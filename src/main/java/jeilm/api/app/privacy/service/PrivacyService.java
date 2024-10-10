package jeilm.api.app.privacy.service;

import java.util.List;

import jeilm.api.app.privacy.vo.PrivacyVO;

public interface PrivacyService {

	/**
	 * 개인정보 조회
	 * @param privacyVO
	 * @return
	 * @throws Exception
	 */
	PrivacyVO selectPrivacy(PrivacyVO privacyVO) throws Exception;
	
	/**
	 * 개인정보 조회 최신
	 * @param privacyVO
	 * @return
	 * @throws Exception
	 */
	PrivacyVO selectPrivacyTop(PrivacyVO privacyVO) throws Exception;
	
	/**
	 * 개인정보 리스트
	 * @param privacyVO
	 * @return
	 * @throws Exception
	 */
	List<PrivacyVO> selectPrivacyList(PrivacyVO privacyVO) throws Exception;
	
}
