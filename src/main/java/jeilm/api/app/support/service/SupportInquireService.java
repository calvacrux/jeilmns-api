package jeilm.api.app.support.service;

import jeilm.api.app.support.vo.SupportInquireVO;

public interface SupportInquireService {

	/**
	 * 고객지원 문의현황 입력
	 * @param supportInquireVO
	 * @return
	 * @throws Exception
	 */
	int insertSupportInquire(SupportInquireVO supportInquireVO) throws Exception;
	
}
