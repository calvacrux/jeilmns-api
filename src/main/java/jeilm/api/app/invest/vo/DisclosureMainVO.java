package jeilm.api.app.invest.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
public class DisclosureMainVO implements Comparable<DisclosureMainVO> {

	// 구분
	private String post_type = "disclosure";	// 구분값

	// 기본
	private String post_sn;						// 포스트 일련번호
	@JsonIgnore
	private String board_id;					// 게시판 아이디
	private String post_title;					// 포스트 제목
	private String reg_dt;						// 등록일시
	
	// 요청
	@JsonProperty(access = Access.WRITE_ONLY)
	private String lang;						// 언어(ko, en, cn)

	@Override
	public int compareTo(DisclosureMainVO vo) {
		if (vo.reg_dt.compareTo(this.reg_dt) > 0) {
			return 1;
		} else if (vo.reg_dt.compareTo(this.reg_dt) < 0) {
			return -1;
		} else {
			return 0;
		}
	}
		
}
