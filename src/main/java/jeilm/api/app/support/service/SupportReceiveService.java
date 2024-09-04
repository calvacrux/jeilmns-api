package jeilm.api.app.support.service;

import java.util.List;

import jeilm.api.app.support.vo.SupportReceiveVO;

public interface SupportReceiveService {

	/**
	 * 고객지원 메일수신자 리스트
	 * @param supportReceiveVO
	 * @return
	 * @throws Exception
	 */
	List<SupportReceiveVO> selectSupportReceiveList(SupportReceiveVO supportReceiveVO) throws Exception;
	
}
