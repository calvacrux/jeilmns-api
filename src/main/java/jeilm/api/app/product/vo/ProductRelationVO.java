package jeilm.api.app.product.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class ProductRelationVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 기본
	@JsonIgnore
	private String info_sn_org;				// 메인제품 번호
	
	// 조인 - 제품정보
	private String category_sn;				// 카테고리 번호
	private String category_nm;				// 카테고리 이름
	private String info_sn;					// 정보 일련번호
	private String info_title;				// 제품 타이틀
	
}
