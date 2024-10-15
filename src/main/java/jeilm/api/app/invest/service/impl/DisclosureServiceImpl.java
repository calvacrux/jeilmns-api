package jeilm.api.app.invest.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import jeilm.api.app.invest.service.DisclosureService;
import jeilm.api.app.invest.service.mapper.DisclosureMapper;
import jeilm.api.app.invest.vo.DisclosureListVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DisclosureServiceImpl implements DisclosureService {
	
	private final DisclosureMapper disclosureMapper;

	@Override
	public List<DisclosureListVO> selectPostList(DisclosureListVO disclosureListVO) throws Exception {
		return disclosureMapper.selectPostList(disclosureListVO);
	}
	
	@Override
	public int selectPostCount(DisclosureListVO disclosureListVO) throws Exception {
		return disclosureMapper.selectPostCount(disclosureListVO);
	}

}
