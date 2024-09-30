package jeilm.api.app.career.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jeilm.api.cmm.constant.AppConstant;
import lombok.Data;

@Data
public class CareerPositionVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 기본
	private String position_sn;				// 채용공고 번호
	@JsonIgnore
	private String position_id;				// 채용공고 아이디
	@JsonIgnore
	private String position_cat;			// 채용공고 카테고리
	private String position_desc;			// 채용공고 직무설명
	private String position_require;		// 채용공고 자격요전
	
	// 조인 - 카테고리
	private String position_cat_nm;			// 카테고리 이름
	
	// 파일 - 본문
	@JsonIgnore
	private String file_user_nm_main;		// 썸네일 파일 이름
	@JsonIgnore
	private String file_path_main;			// 썸네일 파일 경로
	
	// 파일 - URL
	public String file_url_main;			// 썸네일 파일 URL
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
