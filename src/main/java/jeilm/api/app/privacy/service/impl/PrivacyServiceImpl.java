package jeilm.api.app.privacy.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import jeilm.api.app.privacy.service.PrivacyService;
import jeilm.api.app.privacy.service.mapper.PrivacyMapper;
import jeilm.api.app.privacy.vo.PrivacyVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrivacyServiceImpl implements PrivacyService {
	
	private final PrivacyMapper privacyMapper;

	@Override
	public PrivacyVO selectPrivacy(PrivacyVO privacyVO) throws Exception {
		return privacyMapper.selectPrivacy(privacyVO);
	}
	
	@Override
	public PrivacyVO selectPrivacyTop(PrivacyVO privacyVO) throws Exception {
		return privacyMapper.selectPrivacyTop(privacyVO);
	}

	@Override
	public List<PrivacyVO> selectPrivacyList(PrivacyVO privacyVO) throws Exception {
		return privacyMapper.selectPrivacyList(privacyVO);
	}

}
