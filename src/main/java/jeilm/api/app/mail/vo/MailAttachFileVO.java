package jeilm.api.app.mail.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class MailAttachFileVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String file_user_nm;		//첨부파일 이름
	private String file_path;			//첨부파일 경로

}
