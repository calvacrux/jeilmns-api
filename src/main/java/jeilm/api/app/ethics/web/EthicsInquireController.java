package jeilm.api.app.ethics.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

@Controller
@RequiredArgsConstructor
public class EthicsInquireController {
	
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
	public ResponseEntity<?> wrtieEthicsInquireProcess(EthicsInquireVO ethicsInquireVO, @RequestParam("upload_file") List<MultipartFile> multipartFiles, SessionStatus status, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		String resultCode = null;
		String resultMessage = null;
		
		String result = ethicsInquireService.insertEthicsInquireReturnSn(ethicsInquireVO, multipartFiles);
		
		if (result.equals("")) {
			resultCode = "ERROR";
			resultMessage = "오류가 발생했습니다.";
		} else {
			resultCode = "OK";
			resultMessage = "정상 완료했습니다.";
			
			// 첨부 파일
			List<MailAttachFileVO> resultFileList = ethicsInquireService.selectEthicsInquireFileList(ethicsInquireVO);
						
			// 메일 발송
			EthicsReceiveVO receiveVO = new EthicsReceiveVO();
			List<EthicsReceiveVO> resultList = ethicsReceiveService.selectEthicsReceiveList(receiveVO);
			
			for (EthicsReceiveVO vo : resultList) {
				MailVO mailVO = new MailVO();
				// mailVO.setFromAddress(fromMailAddress);
				mailVO.setFromInternetAddress(new InternetAddress(fromMailAddress, "홈페이지시스템"));
				//mailVO.setToAddress(vo.getReceive_mail());
				mailVO.setToInternetAddress(new InternetAddress(vo.getReceive_mail(), vo.getReceive_nm()));
				mailVO.setMailSubject("홈페이지 윤리경영 접수 - " + StringUtil.str2html(ethicsInquireVO.getInquire_title()));
				mailVO.setTemplateView("layout/mail/ethics-request");
				
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
				
				// 첨부파일
				mailVO.setFileList(resultFileList);
				mailVO.setContentList(contentList);
				
				mailService.sendMail(mailVO);
			}
		}
		
		map.put("result_code", resultCode);
		map.put("result_message", resultMessage);
		
		status.setComplete(); //double commit 방지  
		
		return JsonResult.success(map);
	}

}
