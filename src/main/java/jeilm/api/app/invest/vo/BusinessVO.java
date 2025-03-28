package jeilm.api.app.invest.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jeilm.api.cmm.constant.AppConstant;
import lombok.Data;

@Data
public class BusinessVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
		// 기본
		private String post_sn;						// 포스트 일련번호
		@JsonIgnore
		private String board_id;					// 게시판 아이디
		private String post_title;					// 포스트 제목
		private String reg_dt;						// 등록일시
		
		// 페이징 및 검색
		@JsonIgnore
		private int page_offset = 0;				// 페이지 오프셋 (page_index - 1) * page_size
		
		// 파일 - 메인
		private String file_user_nm_main;			// 메인 파일 이름
		@JsonIgnore
		private String file_path_main;				// 메인 파일 경로
		
		// 파일 - URL
		public String file_url_main;				// 메인 파일 URL
		public String getFile_url_main() {
			if (file_path_main == null) {
				return null;
			} else {
				return AppConstant.storagePath.concat(file_path_main);
			}
		}
		
		// 요청
		@JsonProperty(access = Access.WRITE_ONLY)
		private String lang;						// 언어(ko, en, cn)
		
		@JsonProperty(access = Access.WRITE_ONLY)
		private int page_index = 1;					// 현재 페이지
		
		@JsonProperty(access = Access.WRITE_ONLY)
		private int page_size = 10;					// 페이지 로우수
		
}
