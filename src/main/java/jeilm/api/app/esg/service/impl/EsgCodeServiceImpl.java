package jeilm.api.app.esg.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import jeilm.api.app.esg.service.EsgCodeService;
import jeilm.api.app.esg.service.mapper.EsgCodeMapper;
import jeilm.api.app.esg.vo.EsgCodeVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EsgCodeServiceImpl implements EsgCodeService {
	
	private final EsgCodeMapper esgCodeMapper;

	@Override
	public List<EsgCodeVO> selectPostList(EsgCodeVO esgCodeVO) throws Exception {
		return esgCodeMapper.selectPostList(esgCodeVO);
	}
	
	@Override
	public int selectPostCount(EsgCodeVO esgCodeVO) throws Exception {
		return esgCodeMapper.selectPostCount(esgCodeVO);
	}

	@Override
	public EsgCodeVO selectTopFile(EsgCodeVO esgCodeVO) throws Exception {
		return esgCodeMapper.selectTopFile(esgCodeVO);
	}

}
