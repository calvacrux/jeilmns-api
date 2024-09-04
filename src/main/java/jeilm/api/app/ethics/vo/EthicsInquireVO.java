package jeilm.api.app.ethics.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class EthicsInquireVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 기본
	private String inquire_sn;			// 윤리경영 번호
	private String customer_nm;			// 고객 이름
	private String customer_tel;		// 고객 전화
	private String customer_mail;		// 고객 메일
	private String inquire_title;		// 윤리경영 제목 
	private String inquire_content;		// 윤리경영 내용
	
}
