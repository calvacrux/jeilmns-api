package jeilm.api.app.support.service.impl;

import org.springframework.stereotype.Service;

import jeilm.api.app.support.service.SupportInquireService;
import jeilm.api.app.support.service.mapper.SupportInquireMapper;
import jeilm.api.app.support.vo.SupportInquireVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupportInquireServiceImpl implements SupportInquireService {
	
	private final SupportInquireMapper supportInquireMapper;

	@Override
	public int insertSupportInquire(SupportInquireVO supportInquireVO) throws Exception {
		return supportInquireMapper.insertSupportInquire(supportInquireVO);
	}

}
