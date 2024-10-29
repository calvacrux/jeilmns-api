package jeilm.api.app.support.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jeilm.api.cmm.constant.AppConstant;
import lombok.Data;

@Data
public class SupportContactVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 기본
	@JsonIgnore
	private String contact_id;				// 연락처 아이디
	private String contact_cat;				// 연락처 구분
	private String contact_nm;				// 연락처 이름
	private String contact_tel;				// 연락처 전화번호
	private String contact_mail;			// 연락처 메일주소
	
	// 파일 - 첨부
	@JsonIgnore
	private String file_user_nm_main;			// 첨부 파일 이름
	@JsonIgnore
	private String file_path_main;				// 첨부 파일 경로
		
	// 파일 - URL
	public String file_url_main;				// 메인 파일 URL
	public String getFile_url_main() {
		if (file_path_main == null) {
			return null;
		} else {
			return AppConstant.storagePath.concat(file_path_main);
		}
	}
	
	// 요청
	@JsonProperty(access = Access.WRITE_ONLY)
	private String lang;						// 언어(ko, en, cn)

}
