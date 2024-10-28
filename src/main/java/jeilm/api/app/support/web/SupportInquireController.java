package jeilm.api.app.support.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import jakarta.mail.internet.InternetAddress;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jeilm.api.app.code.service.CodeService;
import jeilm.api.app.code.vo.CodeVO;
import jeilm.api.app.mail.service.MailService;
import jeilm.api.app.mail.vo.MailAttachFileVO;
import jeilm.api.app.mail.vo.MailContentVO;
import jeilm.api.app.mail.vo.MailVO;
import jeilm.api.app.support.service.SupportInquireService;
import jeilm.api.app.support.service.SupportReceiveService;
import jeilm.api.app.support.vo.SupportInquireVO;
import jeilm.api.app.support.vo.SupportReceiveVO;
import jeilm.api.cmm.json.JsonResult;
import jeilm.api.cmm.util.StringUtil;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SupportInquireController {
	
	@Value("${global.base.url}")
	private String resourceUrl;
	
	@Value("${global.manager.url}")
	private String managerUrl;
	
	@Value("${spring.mail.username}")
	private String fromMailAddress;
	
	private final SupportInquireService supportInquireService;
	private final SupportReceiveService supportReceiveService;
	private final CodeService codeService;
	private final MailService mailService;
	
	/**
	 * 고객지원 문의현황 작성
	 * @param supportInquireVO
	 * @param status
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/v1/support/inquire")
	public ResponseEntity<?> wrtieSupportInquireProcess(SupportInquireVO supportInquireVO, @RequestParam(name = "upload_file", required = false) List<MultipartFile> multipartFiles, SessionStatus status, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		String resultCode = null;
		String resultMessage = null;
		
		String result = supportInquireService.insertSupportInquireReturnSn(supportInquireVO, multipartFiles);
		
		if (result.equals("")) {
			resultCode = "ERROR";
			resultMessage = "오류가 발생했습니다.";
		} else {
			resultCode = "OK";
			resultMessage = "정상 등록했습니다.";
			
			// 오늘날짜
			Date date = new Date();
			SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy.MM.dd");
			String nowDay = dtFormat.format(date);
			
			// 카테고리 코드
			CodeVO codeVO = new CodeVO();
			codeVO.setCode_id(supportInquireVO.getInquire_cat());
			codeVO = codeService.selectCode(codeVO);
			
			// 첨부 파일
			List<MailAttachFileVO> resultFileList = supportInquireService.selectSupportInquireFileList(supportInquireVO);
							
			// 관리자 발송자
			SupportReceiveVO supportReceiveVO = new SupportReceiveVO();
			List<SupportReceiveVO> resultList = supportReceiveService.selectSupportReceiveList(supportReceiveVO);
			
			// 관리자 메일발송 
			for (SupportReceiveVO vo : resultList) {
				MailVO mailVO = new MailVO();
				mailVO.setFromInternetAddress(new InternetAddress(fromMailAddress, "홈페이지시스템"));
				mailVO.setToInternetAddress(new InternetAddress(vo.getReceive_mail(), vo.getReceive_nm()));
				mailVO.setMailSubject("홈페이지 문의 접수 - " + StringUtil.str2html(supportInquireVO.getInquire_title()));
				mailVO.setTemplateView("layout/mail/inquire-request-manager");
				
				// 메일 본문
				List<MailContentVO> contentList = new ArrayList<MailContentVO>();
				
				MailContentVO mailContentVO = new MailContentVO();
				mailContentVO.setContentKey("customer_nm");
				mailContentVO.setContentValue(supportInquireVO.getCustomer_nm());
				contentList.add(mailContentVO);
				
				mailContentVO = new MailContentVO();
				mailContentVO.setContentKey("customer_tel");
				mailContentVO.setContentValue(supportInquireVO.getCustomer_tel());
				contentList.add(mailContentVO);
				
				mailContentVO = new MailContentVO();
				mailContentVO.setContentKey("customer_mail");
				mailContentVO.setContentValue(supportInquireVO.getCustomer_mail());
				contentList.add(mailContentVO);
				
				mailContentVO = new MailContentVO();
				mailContentVO.setContentKey("inquire_title");
				mailContentVO.setContentValue(StringUtil.str2html(supportInquireVO.getInquire_title()));
				contentList.add(mailContentVO);
				
				mailContentVO = new MailContentVO();
				mailContentVO.setContentKey("inquire_content");
				mailContentVO.setContentValue(StringUtil.str2html(supportInquireVO.getInquire_content()));
				contentList.add(mailContentVO);
				
				mailContentVO = new MailContentVO();
				mailContentVO.setContentKey("inquire_cat_nm");
				mailContentVO.setContentValue(codeVO.getCode_nm());
				contentList.add(mailContentVO);
				
				mailContentVO = new MailContentVO();
				mailContentVO.setContentKey("inquire_day");
				mailContentVO.setContentValue(nowDay);
				contentList.add(mailContentVO);
				
				mailContentVO = new MailContentVO();
				mailContentVO.setContentKey("managerUrl");
				mailContentVO.setContentValue(managerUrl);
				contentList.add(mailContentVO);
				
				mailContentVO = new MailContentVO();
				mailContentVO.setContentKey("resourceUrl");
				mailContentVO.setContentValue(resourceUrl);
				contentList.add(mailContentVO);
				
				// 첨부파일
				mailVO.setFileList(resultFileList);
				mailVO.setContentList(contentList);
				
				mailService.sendMail(mailVO);
			}
			
			// 사용자 메일발송
			MailVO mailVO = new MailVO();
			mailVO.setFromInternetAddress(new InternetAddress(fromMailAddress, "제일엠앤에스"));
			mailVO.setToInternetAddress(new InternetAddress(supportInquireVO.getCustomer_mail(), supportInquireVO.getCustomer_nm()));
			mailVO.setMailSubject("제일엠앤에스 문의 접수 - " + StringUtil.str2html(supportInquireVO.getInquire_title()));
			mailVO.setTemplateView("layout/mail/inquire-request-user");
			
			// 메일 본문
			List<MailContentVO> contentList = new ArrayList<MailContentVO>();
			
			MailContentVO mailContentVO = new MailContentVO();
			mailContentVO.setContentKey("customer_nm");
			mailContentVO.setContentValue(supportInquireVO.getCustomer_nm());
			contentList.add(mailContentVO);
			
			mailContentVO = new MailContentVO();
			mailContentVO.setContentKey("customer_tel");
			mailContentVO.setContentValue(supportInquireVO.getCustomer_tel());
			contentList.add(mailContentVO);
			
			mailContentVO = new MailContentVO();
			mailContentVO.setContentKey("customer_mail");
			mailContentVO.setContentValue(supportInquireVO.getCustomer_mail());
			contentList.add(mailContentVO);
			
			mailContentVO = new MailContentVO();
			mailContentVO.setContentKey("inquire_title");
			mailContentVO.setContentValue(StringUtil.str2html(supportInquireVO.getInquire_title()));
			contentList.add(mailContentVO);
			
			mailContentVO = new MailContentVO();
			mailContentVO.setContentKey("inquire_content");
			mailContentVO.setContentValue(StringUtil.str2html(supportInquireVO.getInquire_content()));
			contentList.add(mailContentVO);
			
			mailContentVO = new MailContentVO();
			mailContentVO.setContentKey("inquire_cat_nm");
			mailContentVO.setContentValue(codeVO.getCode_nm());
			contentList.add(mailContentVO);
			
			mailContentVO = new MailContentVO();
			mailContentVO.setContentKey("inquire_day");
			mailContentVO.setContentValue(nowDay);
			contentList.add(mailContentVO);
			
			mailContentVO = new MailContentVO();
			mailContentVO.setContentKey("managerUrl");
			mailContentVO.setContentValue(managerUrl);
			contentList.add(mailContentVO);
			
			mailContentVO = new MailContentVO();
			mailContentVO.setContentKey("resourceUrl");
			mailContentVO.setContentValue(resourceUrl);
			contentList.add(mailContentVO);
			
			// 첨부파일
			mailVO.setFileList(resultFileList);
			mailVO.setContentList(contentList);
			
			mailService.sendMail(mailVO);
		}
		
		map.put("result_code", resultCode);
		map.put("result_message", resultMessage);
		
		status.setComplete(); //double commit 방지  
		
		return JsonResult.success(map);
	}

}
