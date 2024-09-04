package jeilm.api.app.code.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
public class CodeVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String code_id;				//코드 ID
	private String code_nm;				//코드 이름
	
	// 요청
	@JsonProperty(access = Access.WRITE_ONLY)
	private String parent_code_id;		//부모 코드 ID
	
}
