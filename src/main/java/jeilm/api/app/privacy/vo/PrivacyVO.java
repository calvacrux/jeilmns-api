package jeilm.api.app.privacy.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PrivacyVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 기본
	@JsonIgnore
	private String privacy_id;				// 개인정보 아이디
	private String privacy_version;			// 개인정보 버전
	private String privacy_revision;		// 개인정보 개정일자
	private String privacy_content;			// 개인정보 내용
	
	// 요청
	@JsonProperty(access = Access.WRITE_ONLY)
	private String lang;						// 언어(ko, en, cn)
}
