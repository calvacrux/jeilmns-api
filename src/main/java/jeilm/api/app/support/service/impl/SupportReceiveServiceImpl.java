package jeilm.api.app.support.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import jeilm.api.app.support.service.SupportReceiveService;
import jeilm.api.app.support.service.mapper.SupportReceiveMapper;
import jeilm.api.app.support.vo.SupportReceiveVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupportReceiveServiceImpl implements SupportReceiveService {

	private final SupportReceiveMapper supportReceiveMapper;
	
	@Override
	public List<SupportReceiveVO> selectSupportReceiveList(SupportReceiveVO supportReceiveVO) throws Exception {
		return supportReceiveMapper.selectSupportReceiveList(supportReceiveVO);
	}

}
