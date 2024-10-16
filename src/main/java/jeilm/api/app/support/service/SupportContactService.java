package jeilm.api.app.support.service;

import java.util.List;

import jeilm.api.app.support.vo.SupportContactVO;

public interface SupportContactService {

	/**
	 * 연락처 리스트 조회
	 * @param supportContactVO
	 * @return
	 * @throws Exception
	 */
	List<SupportContactVO> selectSupportContactList(SupportContactVO supportContactVO)  throws Exception;
	
}
