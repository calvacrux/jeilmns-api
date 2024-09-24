package jeilm.api.app.invest.service;

import java.util.List;

import jeilm.api.app.invest.vo.AuditVO;

public interface AuditService {

	/**
	 * 포스트 리스트 조회
	 * @param auditVO
	 * @return
	 * @throws Exception
	 */
	List<AuditVO> selectPostList(AuditVO auditVO) throws Exception;
	
	/**
	 * 포스트 카운트 조회
	 * @param auditVO
	 * @return
	 * @throws Exception
	 */
	int selectPostCount(AuditVO auditVO) throws Exception;
}
