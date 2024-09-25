package jeilm.api.app.file.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jeilm.api.cmm.constant.AppConstant;
import lombok.Data;

@Data
public class DownFileVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 기본
	private String file_name;					// 파일 이름
	@JsonIgnore
	private String file_path;				// 파일 경로
	
	// 파일
	public String file_url;					// 파일 URL
	public String getFile_url() {
		if (file_path == null) {
			return null;
		} else {
			return AppConstant.storagePath.concat(file_path);
		}
	}
	
}
