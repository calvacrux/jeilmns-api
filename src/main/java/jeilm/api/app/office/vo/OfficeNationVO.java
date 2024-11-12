package jeilm.api.app.office.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
public class OfficeNationVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 기본
	private String nation_sn;				// 국가 번호
	@JsonIgnore
	private String nation_id;				// 국가 아이디
	private String nation_nm;				// 국가 이름
	private String nation_x;				// 국가 위치 X
	private String nation_y;				// 국가 위치 Y
	private int nation_count;				// 국가의 사무소 카운트
	
	// 조인
	@JsonIgnore
	private String location_id;				// 위치 아이디
	
	// 요청
	@JsonProperty(access = Access.WRITE_ONLY)
	private String lang;						// 언어(ko, en, cn)
}
