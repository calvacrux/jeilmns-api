package jeilm.api.app.office.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jeilm.api.cmm.constant.AppConstant;
import lombok.Data;

@Data
public class OfficeLocationVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 기본
	private String location_sn;				// 위치 번호
	private String nation_sn;				// 국가 번호
	private String nation_nm;				// 국가 이름
	@JsonIgnore
	private String location_id;				// 위치 아이디
	private String domestic_yn;				// 국내여부(Y:예, N:아니오)
	private String location_nm;				// 사업장 이름
	private String location_addr;			// 사업장 주소
	private String location_tel;			// 사업장 전화
	private String location_fax;			// 사업장 팩스
	
	// 파일 - 메인
	@JsonIgnore
	private String file_path_main;				// 메인 파일 경로
	
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
