package jeilm.api.app.finance.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jeilm.api.app.finance.vo.FinanceVO;

@Mapper
public interface FinanceMapper {
	
	/**
	 * 재무 리스트 조회
	 * @param financeVO
	 * @return
	 * @throws Exception
	 */
	List<FinanceVO> selectFinanceList(FinanceVO financeVO)  throws Exception;

}
