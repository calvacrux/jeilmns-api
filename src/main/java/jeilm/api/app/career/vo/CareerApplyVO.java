package jeilm.api.app.career.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class CareerApplyVO implements Serializable {

	private static final long serialVersionUID = 1L;

	// 기본
	private String apply_sn;					// 채용지원 번호
	private String position_sn;					// 채용공고 번호
	private String apply_nm;					// 채용지원 이름
	private String apply_tel;					// 채용지원 전화
	private String apply_mail;					// 채용지원 메일
	private String apply_content;				// 채용지원 내용
	
}
