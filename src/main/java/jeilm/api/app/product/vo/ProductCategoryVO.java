package jeilm.api.app.product.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
public class ProductCategoryVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 기본
	private String category_sn;				// 카테고리 일련번호
	@JsonIgnore
	private String category_id;				// 카테고리 아이디
	private String category_nm;				// 카테고리 이름
		
	// 요청
	@JsonProperty(access = Access.WRITE_ONLY)
	private String lang;						// 언어(ko, en, cn)

}
