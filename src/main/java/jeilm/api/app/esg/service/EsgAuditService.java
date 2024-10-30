package jeilm.api.app.esg.service;

import java.util.List;

import jeilm.api.app.esg.vo.EsgAuditVO;

public interface EsgAuditService {

	/**
	 * 외부감사 리스트 조회
	 * @param esgAuditVO
	 * @return
	 * @throws Exception
	 */
	List<EsgAuditVO> selectEsgAuditList(EsgAuditVO esgAuditVO)  throws Exception;
	
}
