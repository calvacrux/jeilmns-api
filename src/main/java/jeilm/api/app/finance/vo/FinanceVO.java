package jeilm.api.app.finance.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class FinanceVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 기본
	private int finance_year;				// 재무년도
	private long asset_total;				// 자산총계
	private long asset_current;				// 유동자산
	private long asset_current_non;			// 비유동자산
	private long debt_total;					// 부채총계
	private long debt_current;				// 유동부채
	private long debt_current_non;			// 비유동부채
	private long capital_total;				// 자본총계
	private long capital_invest;				// 순투자자본
	private long gain_loss_other_total;		// 기타포괄손익누계액
	private long capital_other;				// 기타자본
	
}
