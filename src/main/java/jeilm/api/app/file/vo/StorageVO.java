package jeilm.api.app.file.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class StorageVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String file_sn;                 //파일 일련번호
	private String file_data_sn;            //파일 데이터 일련번호
	private String file_content_cd;         //파일 컨텐츠 코드
	private String file_user_nm;            //업로드 파일명
	private String file_server_nm;          //서버저장 파일명
	private String file_path;               //파일 PATH
	private String file_alt;                //파일 대체 텍스트
	private String file_mime;               //파일 MIME
	private long file_size;                 //파일 사이즈
	private int file_down_cnt;              //파일 다운로드 횟수
	private String use_yn;                  //사용여부
	private String reg_sn;                  //등록자
	private String reg_dt;                  //등록일자
	private String edt_sn;                  //수정자
	private String edt_dt;                  //수정일자
	
	private String sub_dir;					//서브디렉토리

}
