package jeilm.api.app.esg.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
public class EsgReportVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 기본
	@JsonIgnore
	private String report_id;				// 운영현황 아이디
	private String report_cat;				// 구분
	private String report_open_day;			// 개최일자
	private String report_desc;				// 의안내용
	private String report_pass;				// 가결여부
	
	// 요청
	@JsonProperty(access = Access.WRITE_ONLY)
	private String lang;						// 언어(ko, en, cn)

}
