package jeilm.api.app.invest.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import jeilm.api.app.file.vo.DownFileVO;
import jeilm.api.app.invest.service.InformationService;
import jeilm.api.app.invest.service.mapper.InformationMapper;
import jeilm.api.app.invest.vo.InformationVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InformationServiceImpl implements InformationService {
	
	private final InformationMapper informationMapper;

	@Override
	public List<InformationVO> selectPostList(InformationVO informationVO) throws Exception {
		return informationMapper.selectPostList(informationVO);
	}
	
	@Override
	public int selectPostCount(InformationVO informationVO) throws Exception {
		return informationMapper.selectPostCount(informationVO);
	}

	@Override
	public DownFileVO selectTopFile(InformationVO informationVO) throws Exception {
		return informationMapper.selectTopFile(informationVO);
	}

}
