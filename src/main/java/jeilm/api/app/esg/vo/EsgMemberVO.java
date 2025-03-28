package jeilm.api.app.esg.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
public class EsgMemberVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 기본
	@JsonIgnore
	private String member_id;				// 위원회 아이디
	private String member_cat;				// 위원회명
	private String member_nm;				// 위원장
	private String member_member;			// 위원
	private String member_task;				// 역할
	
	// 요청
	@JsonProperty(access = Access.WRITE_ONLY)
	private String lang;						// 언어(ko, en, cn)

}
