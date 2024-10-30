package jeilm.api.app.esg.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
public class EsgAuditVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 기본
	@JsonIgnore
	private String audit_id;				// 외부감사 아이디
	private String audit_nm;				// 외부감사 법인명
	private String audit_appoint_day;		// 외부감사 선임일
	private String audit_term_start_day;	// 감사대상 시작일
	private String audit_term_end_day;		// 감사대상 종료일
	private String audit_opinion;			// 감사의견
	
	// 요청
	@JsonProperty(access = Access.WRITE_ONLY)
	private String lang;						// 언어(ko, en, cn)

}
