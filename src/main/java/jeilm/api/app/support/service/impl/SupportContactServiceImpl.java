package jeilm.api.app.support.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import jeilm.api.app.support.service.SupportContactService;
import jeilm.api.app.support.service.mapper.SupportContactMapper;
import jeilm.api.app.support.vo.SupportContactVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupportContactServiceImpl implements SupportContactService {
	
	private final SupportContactMapper supportContactMapper;

	@Override
	public List<SupportContactVO> selectSupportContactList(SupportContactVO supportContactVO) throws Exception {
		return supportContactMapper.selectSupportContactList(supportContactVO);
	}

}
