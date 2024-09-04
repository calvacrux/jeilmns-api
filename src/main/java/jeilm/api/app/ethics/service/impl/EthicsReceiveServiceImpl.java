package jeilm.api.app.ethics.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import jeilm.api.app.ethics.service.EthicsReceiveService;
import jeilm.api.app.ethics.service.mapper.EthicsReceiveMapper;
import jeilm.api.app.ethics.vo.EthicsReceiveVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EthicsReceiveServiceImpl implements EthicsReceiveService {
	
	private final EthicsReceiveMapper ethicsReceiveMapper;

	@Override
	public List<EthicsReceiveVO> selectEthicsReceiveList(EthicsReceiveVO ethicsReceiveVO) throws Exception {
		return ethicsReceiveMapper.selectEthicsReceiveList(ethicsReceiveVO);
	}

}
