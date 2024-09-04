package jeilm.api.app.mail.service.impl;

import java.net.URL;
import java.util.List;
import java.util.Objects;

import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import jakarta.activation.DataSource;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.util.ByteArrayDataSource;
import jeilm.api.app.mail.service.MailService;
import jeilm.api.app.mail.vo.MailAttachFileVO;
import jeilm.api.app.mail.vo.MailContentVO;
import jeilm.api.app.mail.vo.MailVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@EnableAsync
public class MailServiceImpl implements MailService {

	@Value("${global.ncloud.storage.path}")
	private String storagePath;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired 
	private SpringTemplateEngine templateEngine;
	
	@Override
	@Async
	public void sendMail(MailVO mailVO) throws Exception {
		
		MimeMessage message = javaMailSender.createMimeMessage(); 
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
		
		// 메일 보내는 사람 - 별칭여부 분기
		if (Objects.nonNull(mailVO.getFromInternetAddress())) {
			helper.setFrom(mailVO.getFromInternetAddress());
		} else {
			helper.setFrom(mailVO.getFromAddress());
		}
		
		// 메일 받는 사람 - 별칭여부 분기
		if (Objects.nonNull(mailVO.getToInternetAddress())) {
			helper.setTo(mailVO.getToInternetAddress());
		} else {
			helper.setTo(mailVO.getToAddress());
		}
		
		helper.setSubject(mailVO.getMailSubject());
		
		//메일 컨텐츠
		Context context = new Context();
		List<MailContentVO> contentList = mailVO.getContentList();
		for (MailContentVO content : contentList) {
			context.setVariable(content.getContentKey(), content.getContentValue());
		}
		
		//메일 템플릿
		String html = null;
		if (StringUtils.hasLength(mailVO.getTemplateView())) {
			html = templateEngine.process(mailVO.getTemplateView(), context);
		} else {
			html = templateEngine.process("layout/mail/base-template", context);
		}
		helper.setText(html, true);
		
		//첨부 파일
		if (!ObjectUtils.isEmpty(mailVO.getFileList())) {
			List<MailAttachFileVO> fileList = mailVO.getFileList();
			for (MailAttachFileVO attachFile : fileList) {
				URL fileUrl = new URL(storagePath + attachFile.getFile_path()); 
				byte[] fileData = IOUtils.toByteArray(fileUrl.openStream());
				DataSource dataSource = new ByteArrayDataSource(fileData, "application/x-www-form-urlencoded");
				
				helper.addAttachment(attachFile.getFile_user_nm(), dataSource);
			}
		}
		
		//메일 발송
		try {
			javaMailSender.send(message);
		} catch (Exception e) {
			log.error("error : " + e.getMessage());
		}
	}

}
