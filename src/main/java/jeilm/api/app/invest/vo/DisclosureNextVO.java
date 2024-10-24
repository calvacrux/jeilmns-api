package jeilm.api.app.invest.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
public class DisclosureNextVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 기본
	@JsonIgnore
	private String board_id;					// 게시판 아이디
	private String post_sn;						// 포스트 일련번호
	private String post_title;					// 포스트 제목
	
	// 요청
	@JsonProperty(access = Access.WRITE_ONLY)
	private String lang;						// 언어(ko, en, cn)
	
}
