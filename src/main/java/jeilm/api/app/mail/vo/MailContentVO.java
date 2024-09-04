package jeilm.api.app.mail.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class MailContentVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String contentKey;		//메일컨텐츠 키
	private String contentValue;	//메일컨텐츠 값

}
