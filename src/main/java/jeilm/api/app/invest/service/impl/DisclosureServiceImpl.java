package jeilm.api.app.invest.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import jeilm.api.app.invest.service.DisclosureService;
import jeilm.api.app.invest.service.mapper.DisclosureMapper;
import jeilm.api.app.invest.vo.DisclosureVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DisclosureServiceImpl implements DisclosureService {
	
	private final DisclosureMapper disclosureMapper;

	@Override
	public List<DisclosureVO> selectPostList(DisclosureVO disclosureVO) throws Exception {
		return disclosureMapper.selectPostList(disclosureVO);
	}
	
	@Override
	public int selectPostCount(DisclosureVO disclosureVO) throws Exception {
		return disclosureMapper.selectPostCount(disclosureVO);
	}

}
