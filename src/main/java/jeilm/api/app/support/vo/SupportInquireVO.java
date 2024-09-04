package jeilm.api.app.support.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class SupportInquireVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 기본
	private String inquire_sn;			// 문의 번호
	private String inquire_cat;			// 문의 카테고리
	private String customer_nm;			// 고객 이름
	private String customer_tel;		// 고객 전화
	private String customer_mail;		// 고객 메일
	private String inquire_title;		// 문의 제목 
	private String inquire_content;		// 문의 내용
		
}
