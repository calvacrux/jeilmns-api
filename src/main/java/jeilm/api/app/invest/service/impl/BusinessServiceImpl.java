package jeilm.api.app.invest.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import jeilm.api.app.invest.service.BusinessService;
import jeilm.api.app.invest.service.mapper.BusinessMapper;
import jeilm.api.app.invest.vo.BusinessVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BusinessServiceImpl implements BusinessService {
	
	private final BusinessMapper businessMapper;

	@Override
	public List<BusinessVO> selectPostList(BusinessVO businessVO) throws Exception {
		return businessMapper.selectPostList(businessVO);
	}
	
	@Override
	public int selectPostCount(BusinessVO businessVO) throws Exception {
		return businessMapper.selectPostCount(businessVO);
	}

}
