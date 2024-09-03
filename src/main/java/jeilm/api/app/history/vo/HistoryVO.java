package jeilm.api.app.history.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jeilm.api.cmm.constant.AppConstant;
import lombok.Data;

@Data
public class HistoryVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 기본
	private int history_year;					// 년
	private int history_month;					// 월
	private String history_content;				// 연혁 내용
	
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
