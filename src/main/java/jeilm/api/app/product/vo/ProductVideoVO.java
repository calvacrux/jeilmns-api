package jeilm.api.app.product.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class ProductVideoVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 기본
	@JsonIgnore
	private String info_sn;						// 제품 번호
	private String video_url;					// 비디오 URL
	
}
