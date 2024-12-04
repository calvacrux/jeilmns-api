package jeilm.api.app.invest.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class DartReportsVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 기본
	private String report_nm;			// 보고서명
	private String rcept_no;			// 접수번호
	private String flr_nm;				// 공시 제출인명
	private String rcept_dt;			// 접수일자
}
