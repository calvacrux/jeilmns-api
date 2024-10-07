package jeilm.api.app.ethics.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import jeilm.api.app.ethics.service.EthicsCodeService;
import jeilm.api.app.ethics.service.mapper.EthicsCodeMapper;
import jeilm.api.app.ethics.vo.EthicsCodeVO;
import jeilm.api.app.file.vo.DownFileVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EthicsCodeServiceImpl implements EthicsCodeService {
	
	private final EthicsCodeMapper ethicsCodeMapper;

	@Override
	public List<EthicsCodeVO> selectPostList(EthicsCodeVO ethicsCodeVO) throws Exception {
		return ethicsCodeMapper.selectPostList(ethicsCodeVO);
	}
	
	@Override
	public int selectPostCount(EthicsCodeVO ethicsCodeVO) throws Exception {
		return ethicsCodeMapper.selectPostCount(ethicsCodeVO);
	}

	@Override
	public DownFileVO selectTopFile(EthicsCodeVO ethicsCodeVO) throws Exception {
		return ethicsCodeMapper.selectTopFile(ethicsCodeVO);
	}

}
