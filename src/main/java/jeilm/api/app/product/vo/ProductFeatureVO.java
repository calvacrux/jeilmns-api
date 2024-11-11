package jeilm.api.app.product.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jeilm.api.cmm.constant.AppConstant;
import lombok.Data;

@Data
public class ProductFeatureVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 기본
	@JsonIgnore
	private String info_sn;						// 제품 번호
	private String feature_title;				// 특징 제목
	private String feature_desc;				// 특징 내용
	
	// 파일 - 메인
	@JsonIgnore
	private String file_path_main;			// 메인 파일 경로
	
	// 파일 - 썸네일 - 01
	public String file_url_main;			// 메인 파일 URL
	public String getFile_url_main() {
		if (file_path_main == null) {
			return null;
		} else {
			return AppConstant.storagePath.concat(file_path_main);
		}
	}
	
}
