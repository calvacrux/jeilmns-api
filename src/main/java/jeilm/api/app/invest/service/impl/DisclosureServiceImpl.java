package jeilm.api.app.invest.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import jeilm.api.app.invest.service.DisclosureService;
import jeilm.api.app.invest.service.mapper.DisclosureMapper;
import jeilm.api.app.invest.vo.DisclosureDetailVO;
import jeilm.api.app.invest.vo.DisclosureListVO;
import jeilm.api.app.invest.vo.DisclosureNextVO;
import jeilm.api.app.invest.vo.DisclosurePrevVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DisclosureServiceImpl implements DisclosureService {
	
	private final DisclosureMapper disclosureMapper;

	@Override
	public DisclosureDetailVO selectPost(DisclosureDetailVO disclosureDetailVO) throws Exception {
		return disclosureMapper.selectPost(disclosureDetailVO);
	}

	@Override
	public DisclosurePrevVO selectPostPrev(DisclosurePrevVO disclosurePrevVO) throws Exception {
		return disclosureMapper.selectPostPrev(disclosurePrevVO);
	}

	@Override
	public DisclosureNextVO selectPostNext(DisclosureNextVO disclosureNextVO) throws Exception {
		return disclosureMapper.selectPostNext(disclosureNextVO);
	}
	
	@Override
	public List<DisclosureListVO> selectPostList(DisclosureListVO disclosureListVO) throws Exception {
		return disclosureMapper.selectPostList(disclosureListVO);
	}
	
	@Override
	public int selectPostCount(DisclosureListVO disclosureListVO) throws Exception {
		return disclosureMapper.selectPostCount(disclosureListVO);
	}

}
