package jeilm.api.app.main.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class MainGnbJsonVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 기본
	private String lang;					// 언어
	private String menu_id;					// 메뉴 아이디
	private String menu_nm;					// 메뉴 이름

}
