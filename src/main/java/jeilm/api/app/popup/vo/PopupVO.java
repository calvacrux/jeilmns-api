package jeilm.api.app.popup.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jeilm.api.cmm.constant.AppConstant;
import lombok.Data;

@Data
public class PopupVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 기본
	private String popup_sn;				// 팝업 번호
	@JsonIgnore
	private String popup_id;				// 팝업 아이디
	private String popup_cat;				// 팝업 카테고리 코드
	private String popup_cat_nm;			// 팝업 카테고리 이름
	private String popup_title;				// 팝업 제목
	private String popup_content;			// 팝업 내용
	private int popup_width;				// 팝업 넓이
	private int popup_height;				// 팝업 높이
	private String link_url;				// 링크 URL
	
	// 파일 - 본문
	@JsonIgnore
	private String file_user_nm_body;			// 본문 파일 이름
	@JsonIgnore
	private String file_path_body;				// 본문 파일 경로
	
	// 파일 - URL
	public String file_url_body;				// 본문 파일 URL
	public String getFile_url_body() {
		if (file_path_body == null) {
			return null;
		} else {
			return AppConstant.storagePath.concat(file_path_body);
		}
	}
	
	// 요청
	@JsonProperty(access = Access.WRITE_ONLY)
	private String lang;						// 언어(ko, en, cn)
	
}
