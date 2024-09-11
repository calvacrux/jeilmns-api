package jeilm.api.app.popup.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class PopupVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 기본
	private String popup_sn;				// 팝업 번호
	private String popup_title;				// 팝업 제목
	private String popup_content;			// 팝업 내용
	private int popup_width;				// 팝업 넓이
	private int popup_height;				// 팝업 높이
	
}
