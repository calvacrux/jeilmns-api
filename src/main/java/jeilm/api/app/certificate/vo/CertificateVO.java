package jeilm.api.app.certificate.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jeilm.api.cmm.constant.AppConstant;
import lombok.Data;

@Data
public class CertificateVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	// 기본
	private String certificate_title;			// 인증서 제목
	
	// 파일 - 썸네일
	@JsonIgnore
	private String file_user_nm_thumb;			// 썸네일 파일 이름
	@JsonIgnore
	private String file_path_thumb;			    // 썸네일 파일 경로
	
	// 파일 - 메인
	@JsonIgnore
	private String file_user_nm_main;			// 메인 파일 이름
	@JsonIgnore
	private String file_path_main;				// 메인 파일 경로
	
	// 파일 - 썸네일 URL
	public String file_url_thumb;				// 썸네일 파일 URL
	public String getFile_url_thumb() {
		if (file_path_thumb == null) {
			return null;
		} else {
			return AppConstant.storagePath.concat(file_path_thumb);
		}
	}
		
	// 파일 - 메인 URL
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
