package jeilm.api.app.product.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jeilm.api.cmm.constant.AppConstant;
import lombok.Data;

@Data
public class ProductInfoListVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 기본
	private String info_sn;						// 정보 일련번호
	private String category_sn;					// 카테고리 번호
	private String info_title;					// 정보 타이틀
	private String info_title_sub;				// 정보 타이틀 서브
	
	// 조인 - 카테고리
	@JsonIgnore
	private String category_id;					// 카테고리 아이디
	private String category_nm;					// 카테고리 이름
	
	// 파일 - 썸네일 - 01
	@JsonIgnore
	private String file_path_thumb_01;			// 썸네일 파일 경로
	
	// 파일 - 썸네일 - 01
	public String file_url_thumb_01;			// 썸네일 파일 URL
	public String getFile_url_thumb_01() {
		if (file_path_thumb_01 == null) {
			return null;
		} else {
			return AppConstant.storagePath.concat(file_path_thumb_01);
		}
	}
	
	// 요청
	@JsonProperty(access = Access.WRITE_ONLY)
	private String lang;						// 언어(ko, en, cn)

}
