package jeilm.api.app.esg.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import jeilm.api.app.esg.service.EsgAuditService;
import jeilm.api.app.esg.service.mapper.EsgAuditMapper;
import jeilm.api.app.esg.vo.EsgAuditVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EsgAuditServiceImpl implements EsgAuditService {
	
	private final EsgAuditMapper esgAuditMapper;

	@Override
	public List<EsgAuditVO> selectEsgAuditList(EsgAuditVO esgAuditVO) throws Exception {
		return esgAuditMapper.selectEsgAuditList(esgAuditVO);
	}

}
