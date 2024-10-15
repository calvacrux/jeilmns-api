package jeilm.api.app.board.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jeilm.api.cmm.constant.AppConstant;
import lombok.Data;

@Data
public class InvestBoardDetailVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 기본
	private String post_sn;						// 포스트 일련번호
	private String post_title;					// 포스트 제목
	private String post_content;				// 포스트 내용
	private int view_cnt;						// 포스트 뷰카운트
	private String link_url;					// 링크 URL
	private String reg_dt;						// 등록일시
	
	// 파일 - 본문
	@JsonIgnore
	private String file_user_nm_body;			// 본문 파일 이름
	@JsonIgnore
	private String file_path_body;				// 본문 파일 경로
	
	// 파일 - URL
	public String file_url_body;				// 본문 파일 URL
	public String getFile_url_body() {
		if (file_path_body == null) {
			return null;
		} else {
			return AppConstant.storagePath.concat(file_path_body);
		}
	}
	
	// 파일 - 첨부
	private String file_user_nm_attach;			// 첨부 파일 이름
	@JsonIgnore
	private String file_path_attach;			// 첨부 파일 경로
	
	// 파일 - URL
	public String file_url_attach;				// 첨부 파일 URL
	public String getFile_url_attach() {
		if (file_path_attach == null) {
			return null;
		} else {
			return AppConstant.storagePath.concat(file_path_attach);
		}
	}

}
