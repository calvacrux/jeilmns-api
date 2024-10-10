package jeilm.api.app.privacy.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jeilm.api.app.privacy.vo.PrivacyVO;

@Mapper
public interface PrivacyMapper {

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
