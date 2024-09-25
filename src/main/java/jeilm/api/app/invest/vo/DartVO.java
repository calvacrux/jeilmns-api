package jeilm.api.app.invest.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class DartVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 요청
	private int page_no = 1;				// 페이지 번호
	private int page_count = 10;			// 페이지 별 건수
	
}
