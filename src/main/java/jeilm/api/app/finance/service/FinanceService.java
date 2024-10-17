package jeilm.api.app.finance.service;

import java.util.List;

import jeilm.api.app.finance.vo.FinanceVO;

public interface FinanceService {
	
	/**
	 * 재무 리스트 조회
	 * @param financeVO
	 * @return
	 * @throws Exception
	 */
	List<FinanceVO> selectFinanceList(FinanceVO financeVO)  throws Exception;

}
