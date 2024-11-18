package jeilm.api.app.career.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
public class CareerPositionTitleVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 기본
	private String position_sn;				// 채용공고 번호
	private String position_title;			// 채용공고 제목
	@JsonIgnore
	private String position_id;				// 채용공고 아이디
	
	// 요청
	@JsonProperty(access = Access.WRITE_ONLY)
	private String lang;						// 언어(ko, en, cn)

}
