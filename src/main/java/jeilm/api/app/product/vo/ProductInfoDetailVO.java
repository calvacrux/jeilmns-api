package jeilm.api.app.product.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jeilm.api.cmm.constant.AppConstant;
import lombok.Data;

@Data
public class ProductInfoDetailVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 기본
	private String info_sn;						// 정보 일련번호
	private String category_sn;					// 카테고리 번호
	private String info_title;					// 정보 타이틀
	private String info_title_sub;				// 정보 타이틀 서브
	private String info_desc;					// 정보 설명
	
	// 조인 - 카테고리
	@JsonIgnore
	private String category_id;					// 카테고리 아이디
	private String category_nm;					// 카테고리 이름
	
	// 파일 - 썸네일 - 메인
	@JsonIgnore
	private String file_path_thumb_main;			// 썸네일 파일 경로
	
	// 파일 - 썸네일 - 메인
	public String file_url_thumb_main;			// 썸네일 파일 URL
	public String getFile_url_thumb_main() {
		if (file_path_thumb_main == null) {
			return null;
		} else {
			return AppConstant.storagePath.concat(file_path_thumb_main);
		}
	}
	
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
	
	// 파일 - 썸네일 - 02
	@JsonIgnore
	private String file_path_thumb_02;			// 썸네일 파일 경로
	
	// 파일 - 썸네일 - 02
	public String file_url_thumb_02;			// 썸네일 파일 URL
	public String getFile_url_thumb_02() {
		if (file_path_thumb_02 == null) {
			return null;
		} else {
			return AppConstant.storagePath.concat(file_path_thumb_02);
		}
	}
	
	// 파일 - 썸네일 - 03
	@JsonIgnore
	private String file_path_thumb_03;			// 썸네일 파일 경로
	
	// 파일 - 썸네일 - 03
	public String file_url_thumb_03;			// 썸네일 파일 URL
	public String getFile_url_thumb_03() {
		if (file_path_thumb_03 == null) {
			return null;
		} else {
			return AppConstant.storagePath.concat(file_path_thumb_03);
		}
	}
	
	// 파일 - 썸네일 - 04
	@JsonIgnore
	private String file_path_thumb_04;			// 썸네일 파일 경로
	
	// 파일 - 썸네일 - 04
	public String file_url_thumb_04;			// 썸네일 파일 URL
	public String getFile_url_thumb_04() {
		if (file_path_thumb_04 == null) {
			return null;
		} else {
			return AppConstant.storagePath.concat(file_path_thumb_04);
		}
	}
	
	// 파일 - 썸네일 - 05
	@JsonIgnore
	private String file_path_thumb_05;			// 썸네일 파일 경로
	
	// 파일 - 썸네일 - 05
	public String file_url_thumb_05;			// 썸네일 파일 URL
	public String getFile_url_thumb_05() {
		if (file_path_thumb_05 == null) {
			return null;
		} else {
			return AppConstant.storagePath.concat(file_path_thumb_05);
		}
	}
	
	// 파일 - 스펙
	@JsonIgnore
	private String file_path_spec;				// 스펙 파일 경로
	
	// 파일 - 썸네일 - 05
	public String file_url_spec;			// 스펙 파일 URL
	public String getFile_url_spec() {
		if (file_path_spec == null) {
			return null;
		} else {
			return AppConstant.storagePath.concat(file_path_spec);
		}
	}
	
	// 요청
	@JsonProperty(access = Access.WRITE_ONLY)
	private String lang;						// 언어(ko, en, cn)

}
