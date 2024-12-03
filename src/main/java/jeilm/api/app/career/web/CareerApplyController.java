 package jeilm.api.app.career.web;

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
import jeilm.api.app.career.service.CareerApplyService;
import jeilm.api.app.career.service.CareerPositionService;
import jeilm.api.app.career.vo.CareerApplyVO;
import jeilm.api.app.career.vo.CareerPositionVO;
import jeilm.api.app.career.vo.CareerReceiveVO;
import jeilm.api.app.mail.service.MailService;
import jeilm.api.app.mail.vo.MailAttachFileVO;
import jeilm.api.app.mail.vo.MailContentVO;
import jeilm.api.app.mail.vo.MailVO;
import jeilm.api.cmm.json.JsonResult;
import jeilm.api.cmm.util.StringUtil;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CareerApplyController {

	@Value("${global.base.url}")
	private String resourceUrl;
	
	@Value("${global.manager.url}")
	private String managerUrl;
	
	@Value("${spring.mail.username}")
	private String fromMailAddress;
	
	private final CareerApplyService careerApplyService;
	private final CareerPositionService careerPositionService;
	// private final CareerReceiveService careerReceiveService;
	private final MailService mailService;
	
	@PostMapping("/v1/career/apply")
	public ResponseEntity<?> wrtieCareerApplyProcess(CareerApplyVO careerApplyVO, @RequestParam(name = "upload_file_profile", required = false) MultipartFile multipartFileProfile, @RequestParam(name = "upload_file_portfolio", required = false) MultipartFile multipartFilePortfolio, @RequestParam(name = "upload_file_etc", required = false) MultipartFile multipartFileEtc, SessionStatus status, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		String resultCode = null;
		String resultMessage = null;
		
		// 코드
		CareerPositionVO careerPositionVO = new CareerPositionVO();
		careerPositionVO.setPosition_sn(careerApplyVO.getPosition_sn());
		careerPositionVO = careerPositionService.selectPosition(careerPositionVO);
		
		String result = careerApplyService.insertCareerApplyReturnSn(careerApplyVO, multipartFileProfile, multipartFilePortfolio, multipartFileEtc);
		
		if (result.equals("")) {
			resultCode = "ERROR";
			resultMessage = "오류가 발생했습니다.";
		} else {
			resultCode = "OK";
			resultMessage = "정상 등록되었습니다.";
			
			// 오늘날짜
			Date date = new Date();
			SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy.MM.dd");
			String nowDay = dtFormat.format(date);
			
			// 첨부 파일
			List<MailAttachFileVO> resultFileList = careerApplyService.selectCareerApplyFileList(careerApplyVO);
						
			// 메일 발송 - 관리자
			// CareerReceiveVO receiveVO = new CareerReceiveVO();
			// List<CareerReceiveVO> resultList = careerReceiveService.selectReceiveList(receiveVO);
			
			// 메일 발송 - 채용공고 수신자
			List<CareerReceiveVO> resultList = new ArrayList<CareerReceiveVO>();
			
			if (!careerPositionVO.getReceive_nm_first().isEmpty() && !careerPositionVO.getReceive_mail_first().isEmpty()) {
				CareerReceiveVO receiveVO = new CareerReceiveVO();
				receiveVO.setReceive_nm(careerPositionVO.getReceive_nm_first());
				receiveVO.setReceive_mail(careerPositionVO.getReceive_mail_first());
				resultList.add(receiveVO);
			}
			
			if (!careerPositionVO.getReceive_nm_second().isEmpty() && !careerPositionVO.getReceive_mail_second().isEmpty()) {
				CareerReceiveVO receiveVO = new CareerReceiveVO();
				receiveVO.setReceive_nm(careerPositionVO.getReceive_nm_second());
				receiveVO.setReceive_mail(careerPositionVO.getReceive_mail_second());
				resultList.add(receiveVO);
			}
			
			for (CareerReceiveVO vo : resultList) {
				MailVO mailVO = new MailVO();
				// mailVO.setFromAddress(fromMailAddress);
				mailVO.setFromInternetAddress(new InternetAddress(fromMailAddress, "홈페이지시스템"));
				//mailVO.setToAddress(vo.getReceive_mail());
				mailVO.setToInternetAddress(new InternetAddress(vo.getReceive_mail(), vo.getReceive_nm()));
				mailVO.setMailSubject("[제일엠앤에스] " + StringUtil.str2html(careerApplyVO.getApply_nm()) + "님의 채용지원이 접수 되었습니다.");
				mailVO.setTemplateView("layout/mail/career-apply-manager");
				
				// 메일 본문
				List<MailContentVO> contentList = new ArrayList<MailContentVO>();
				
				MailContentVO mailContentVO = new MailContentVO();
				mailContentVO.setContentKey("positoin_cat_nm");
				mailContentVO.setContentValue(careerPositionVO.getPosition_cat_nm());
				contentList.add(mailContentVO);
				
				mailContentVO = new MailContentVO();
				mailContentVO.setContentKey("apply_nm");
				mailContentVO.setContentValue(careerApplyVO.getApply_nm());
				contentList.add(mailContentVO);
				
				mailContentVO = new MailContentVO();
				mailContentVO.setContentKey("apply_tel");
				mailContentVO.setContentValue(careerApplyVO.getApply_tel());
				contentList.add(mailContentVO);
				
				mailContentVO = new MailContentVO();
				mailContentVO.setContentKey("apply_mail");
				mailContentVO.setContentValue(careerApplyVO.getApply_mail());
				contentList.add(mailContentVO);
				
				mailContentVO = new MailContentVO();
				mailContentVO.setContentKey("apply_content");
				mailContentVO.setContentValue(StringUtil.str2html(careerApplyVO.getApply_content()));
				contentList.add(mailContentVO);
				
				mailContentVO = new MailContentVO();
				mailContentVO.setContentKey("apply_day");
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
			
			// 메일 발송 - 지원자
			MailVO mailApplyVO = new MailVO();
			mailApplyVO.setFromInternetAddress(new InternetAddress(fromMailAddress, "제일엠앤에스"));
			mailApplyVO.setToInternetAddress(new InternetAddress(careerApplyVO.getApply_mail(), careerApplyVO.getApply_nm()));
			
			// 언어분기
			String lang = careerApplyVO.getLang();
			switch (lang) {
				case "ko": 
					mailApplyVO.setTemplateView("layout/mail/career-apply-user-ko");
					mailApplyVO.setMailSubject("[제일엠앤에스] " + StringUtil.str2html(careerApplyVO.getApply_nm()) + "님, 채용지원이 정상접수 되었습니다.");
					break;
				case "en": 
					mailApplyVO.setTemplateView("layout/mail/career-apply-user-en");
					mailApplyVO.setMailSubject("[Jeil M&S] Dear " + StringUtil.str2html(careerApplyVO.getApply_nm()) + ", Your job application has been received successfully.");
					break;
				case "cn": 
					mailApplyVO.setTemplateView("layout/mail/career-apply-user-cn");
					mailApplyVO.setMailSubject("[Jeil M&S] 亲爱的" + StringUtil.str2html(careerApplyVO.getApply_nm()) + ", 您的求职申请已成功收到");
					break;
				default: 
					mailApplyVO.setTemplateView("layout/mail/career-apply-user-ko");
					mailApplyVO.setMailSubject("[제일엠앤에스] " + StringUtil.str2html(careerApplyVO.getApply_nm()) + "님, 채용지원이 정상접수 되었습니다.");
					break;
			}
			
			// 메일 본문
			List<MailContentVO> contentList = new ArrayList<MailContentVO>();
			
			MailContentVO mailContentVO = new MailContentVO();
			mailContentVO.setContentKey("positoin_cat_nm");
			mailContentVO.setContentValue(careerPositionVO.getPosition_cat_nm());
			contentList.add(mailContentVO);
			
			mailContentVO = new MailContentVO();
			mailContentVO.setContentKey("apply_nm");
			mailContentVO.setContentValue(careerApplyVO.getApply_nm());
			contentList.add(mailContentVO);
			
			mailContentVO = new MailContentVO();
			mailContentVO.setContentKey("apply_tel");
			mailContentVO.setContentValue(careerApplyVO.getApply_tel());
			contentList.add(mailContentVO);
			
			mailContentVO = new MailContentVO();
			mailContentVO.setContentKey("apply_mail");
			mailContentVO.setContentValue(careerApplyVO.getApply_mail());
			contentList.add(mailContentVO);
			
			mailContentVO = new MailContentVO();
			mailContentVO.setContentKey("apply_content");
			mailContentVO.setContentValue(StringUtil.str2html(careerApplyVO.getApply_content()));
			contentList.add(mailContentVO);
			
			mailContentVO = new MailContentVO();
			mailContentVO.setContentKey("apply_day");
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
			mailApplyVO.setFileList(resultFileList);
			mailApplyVO.setContentList(contentList);
			
			mailService.sendMail(mailApplyVO);
			
		}
		
		map.put("result_code", resultCode);
		map.put("result_message", resultMessage);
		
		status.setComplete(); //double commit 방지  
		
		return JsonResult.success(map);
	}
	
}
