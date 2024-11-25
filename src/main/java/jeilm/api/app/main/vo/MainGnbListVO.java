package jeilm.api.app.main.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class MainGnbListVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 기본
	private String menu_type;				// 메뉴 타입
	private String sort_no;					// 정렬 순서
	private String lang;					// 언어
	private String menu_id;					// 메뉴 아이디
	private String menu_nm;					// 메뉴 이름

}
