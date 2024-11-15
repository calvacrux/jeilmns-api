package jeilm.api.app.ethics.web;

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
import jeilm.api.app.ethics.service.EthicsInquireService;
import jeilm.api.app.ethics.service.EthicsReceiveService;
import jeilm.api.app.ethics.vo.EthicsInquireVO;
import jeilm.api.app.ethics.vo.EthicsReceiveVO;
import jeilm.api.app.mail.service.MailService;
import jeilm.api.app.mail.vo.MailAttachFileVO;
import jeilm.api.app.mail.vo.MailContentVO;
import jeilm.api.app.mail.vo.MailVO;
import jeilm.api.cmm.json.JsonResult;
import jeilm.api.cmm.util.StringUtil;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class EthicsInquireController {
	
	@Value("${global.base.url}")
	private String resourceUrl;
	
	@Value("${global.manager.url}")
	private String managerUrl;
	
	@Value("${spring.mail.username}")
	private String fromMailAddress;
	
	private final EthicsInquireService ethicsInquireService;
	private final EthicsReceiveService ethicsReceiveService;
	private final MailService mailService;
	
	/**
	 * 윤리경영 제보현황 작성
	 * @param ethicsInquireVO
	 * @param multipartFiles
	 * @param status
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/v1/ethics/inquire")
	public ResponseEntity<?> wrtieEthicsInquireProcess(EthicsInquireVO ethicsInquireVO, @RequestParam(name = "upload_file", required = false) List<MultipartFile> multipartFiles, SessionStatus status, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		String resultCode = null;
		String resultMessage = null;
		
		String result = ethicsInquireService.insertEthicsInquireReturnSn(ethicsInquireVO, multipartFiles);
		
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
			
			// 첨부 파일
			List<MailAttachFileVO> resultFileList = ethicsInquireService.selectEthicsInquireFileList(ethicsInquireVO);
						
			// 관리자 메일발송
			EthicsReceiveVO receiveVO = new EthicsReceiveVO();
			List<EthicsReceiveVO> resultList = ethicsReceiveService.selectEthicsReceiveList(receiveVO);
			
			for (EthicsReceiveVO vo : resultList) {
				MailVO mailVO = new MailVO();
				mailVO.setFromInternetAddress(new InternetAddress(fromMailAddress, "홈페이지시스템"));
				mailVO.setToInternetAddress(new InternetAddress(vo.getReceive_mail(), vo.getReceive_nm()));
				mailVO.setMailSubject("[제일엠앤에스] 윤리경영접수 - " + StringUtil.str2html(ethicsInquireVO.getInquire_title()));
				mailVO.setTemplateView("layout/mail/ethics-request-manager");
				
				// 메일 본문
				List<MailContentVO> contentList = new ArrayList<MailContentVO>();
				
				MailContentVO mailContentVO = new MailContentVO();
				mailContentVO.setContentKey("customer_nm");
				mailContentVO.setContentValue(ethicsInquireVO.getCustomer_nm());
				contentList.add(mailContentVO);
				
				mailContentVO = new MailContentVO();
				mailContentVO.setContentKey("customer_tel");
				mailContentVO.setContentValue(ethicsInquireVO.getCustomer_tel());
				contentList.add(mailContentVO);
				
				mailContentVO = new MailContentVO();
				mailContentVO.setContentKey("customer_mail");
				mailContentVO.setContentValue(ethicsInquireVO.getCustomer_mail());
				contentList.add(mailContentVO);
				
				mailContentVO = new MailContentVO();
				mailContentVO.setContentKey("inquire_title");
				mailContentVO.setContentValue(StringUtil.str2html(ethicsInquireVO.getInquire_title()));
				contentList.add(mailContentVO);
				
				mailContentVO = new MailContentVO();
				mailContentVO.setContentKey("inquire_content");
				mailContentVO.setContentValue(StringUtil.str2html(ethicsInquireVO.getInquire_content()));
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
			mailVO.setToInternetAddress(new InternetAddress(ethicsInquireVO.getCustomer_mail(), ethicsInquireVO.getCustomer_nm()));
			mailVO.setMailSubject("[제일엠앤에스] 윤리경영접수 - " + StringUtil.str2html(ethicsInquireVO.getInquire_title()));
			
			// 언어분기
			String lang = ethicsInquireVO.getLang();
			switch (lang) {
				case "ko": mailVO.setTemplateView("layout/mail/ethics-request-user-ko");
					break;
				case "en": mailVO.setTemplateView("layout/mail/ethics-request-user-en");
					break;
				case "cn": mailVO.setTemplateView("layout/mail/ethics-request-user-cn");
					break;
				default: mailVO.setTemplateView("layout/mail/ethics-request-user-ko");
					break;
			}
			
			// 메일 본문
			List<MailContentVO> contentList = new ArrayList<MailContentVO>();
			
			MailContentVO mailContentVO = new MailContentVO();
			mailContentVO.setContentKey("customer_nm");
			mailContentVO.setContentValue(ethicsInquireVO.getCustomer_nm());
			contentList.add(mailContentVO);
			
			mailContentVO = new MailContentVO();
			mailContentVO.setContentKey("customer_tel");
			mailContentVO.setContentValue(ethicsInquireVO.getCustomer_tel());
			contentList.add(mailContentVO);
			
			mailContentVO = new MailContentVO();
			mailContentVO.setContentKey("customer_mail");
			mailContentVO.setContentValue(ethicsInquireVO.getCustomer_mail());
			contentList.add(mailContentVO);
			
			mailContentVO = new MailContentVO();
			mailContentVO.setContentKey("inquire_title");
			mailContentVO.setContentValue(StringUtil.str2html(ethicsInquireVO.getInquire_title()));
			contentList.add(mailContentVO);
			
			mailContentVO = new MailContentVO();
			mailContentVO.setContentKey("inquire_content");
			mailContentVO.setContentValue(StringUtil.str2html(ethicsInquireVO.getInquire_content()));
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
