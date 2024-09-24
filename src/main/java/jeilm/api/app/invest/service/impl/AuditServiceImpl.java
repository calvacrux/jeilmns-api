package jeilm.api.app.invest.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import jeilm.api.app.invest.service.AuditService;
import jeilm.api.app.invest.service.mapper.AuditMapper;
import jeilm.api.app.invest.vo.AuditVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuditServiceImpl implements AuditService {
	
	private final AuditMapper auditMapper;

	@Override
	public List<AuditVO> selectPostList(AuditVO auditVO) throws Exception {
		return auditMapper.selectPostList(auditVO);
	}
	
	@Override
	public int selectPostCount(AuditVO auditVO) throws Exception {
		return auditMapper.selectPostCount(auditVO);
	}

}
