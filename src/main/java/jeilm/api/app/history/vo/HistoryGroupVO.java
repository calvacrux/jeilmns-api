package jeilm.api.app.history.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
public class HistoryGroupVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 기본
	private String group_start_year;			// 그룹 시작년도	
	private String group_end_year;				// 그룹 종료년도
	private String group_title;					// 제목(ko)
	private String group_subtitle;				// 부제목(ko)
	
	// 요청
	@JsonProperty(access = Access.WRITE_ONLY)
	private String lang;						// 언어(ko, en, cn)
}
