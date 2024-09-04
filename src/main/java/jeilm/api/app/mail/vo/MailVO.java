package jeilm.api.app.mail.vo;

import java.io.Serializable;
import java.util.List;

import jakarta.mail.internet.InternetAddress;
import lombok.Data;

@Data
public class MailVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String fromAddress;						//보내는사람
	private InternetAddress fromInternetAddress;	//보내는사람
	private String toAddress;						//받는사람
	private InternetAddress toInternetAddress;		//받는사람
	private String mailSubject;						//메일제목
	private List<MailContentVO> contentList;		//메일내용
	private String templateView;					//매일템플릿
	private List<MailAttachFileVO> fileList;		//첨부파일

}
