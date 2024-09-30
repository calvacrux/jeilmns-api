package jeilm.api.app.support.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;

import jakarta.mail.internet.InternetAddress;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jeilm.api.app.mail.service.MailService;
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
	
	@Value("${spring.mail.username}")
	private String fromMailAddress;
	
	private final SupportInquireService supportInquireService;
	private final SupportReceiveService supportReceiveService;
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
	public ResponseEntity<?> wrtieSupportInquireProcess(@RequestBody SupportInquireVO supportInquireVO, SessionStatus status, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		String resultCode = null;
		String resultMessage = null;
		
		if (supportInquireVO.getInquire_cat().equals("code.inquire.ko.as") || supportInquireVO.getInquire_cat().equals("code.inquire.cn.as") || supportInquireVO.getInquire_cat().equals("code.inquire.en.as")
				|| supportInquireVO.getInquire_cat().equals("code.inquire.ko.estimate") || supportInquireVO.getInquire_cat().equals("code.inquire.cn.estimate") || supportInquireVO.getInquire_cat().equals("code.inquire.en.estimate")) {
			
			int result = supportInquireService.insertSupportInquire(supportInquireVO);
			
			if (result < 1) {
				resultCode = "ERROR";
				resultMessage = "오류가 발생했습니다.";
			} else {
				resultCode = "OK";
				resultMessage = "정상 등록했습니다.";
				
				// 메일 발송
				SupportReceiveVO supportReceiveVO = new SupportReceiveVO();
				List<SupportReceiveVO> resultList = supportReceiveService.selectSupportReceiveList(supportReceiveVO);
				
				for (SupportReceiveVO vo : resultList) {
					MailVO mailVO = new MailVO();
					mailVO.setFromInternetAddress(new InternetAddress(fromMailAddress, "홈페이지시스템"));
					mailVO.setToInternetAddress(new InternetAddress(vo.getReceive_mail(), vo.getReceive_nm()));
					mailVO.setMailSubject("홈페이지 고객지원 접수 - " + StringUtil.str2html(supportInquireVO.getInquire_title()));
					
					// 메일 템플릿 분기
					if (supportInquireVO.getInquire_cat().equals("code.inquire.ko.as") || supportInquireVO.getInquire_cat().equals("code.inquire.cn.as") || supportInquireVO.getInquire_cat().equals("code.inquire.en.as")) {
						mailVO.setTemplateView("layout/mail/inquire-request-as");
					} else if (supportInquireVO.getInquire_cat().equals("code.inquire.ko.estimate") || supportInquireVO.getInquire_cat().equals("code.inquire.cn.estimate") || supportInquireVO.getInquire_cat().equals("code.inquire.en.estimate")) {
						mailVO.setTemplateView("layout/mail/inquire-request-estimate");
					}
					
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
					
					mailVO.setContentList(contentList);
					
					mailService.sendMail(mailVO);
				}
			}
		} else {
			resultCode = "ERROR";
			resultMessage = "코드값이 올바르지 않습니다.";
		}
		
		map.put("result_code", resultCode);
		map.put("result_message", resultMessage);
		
		status.setComplete(); //double commit 방지  
		
		return JsonResult.success(map);
	}

}
