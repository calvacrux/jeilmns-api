package jeilm.api.app.finance.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import jeilm.api.app.finance.service.FinanceService;
import jeilm.api.app.finance.service.mapper.FinanceMapper;
import jeilm.api.app.finance.vo.FinanceVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FinanceServiceImpl implements FinanceService {
	
	private final FinanceMapper financeMapper;

	@Override
	public List<FinanceVO> selectFinanceList(FinanceVO financeVO) throws Exception {
		return financeMapper.selectFinanceList(financeVO);
	}

}
