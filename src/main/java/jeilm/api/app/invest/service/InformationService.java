package jeilm.api.app.invest.service;

import java.util.List;

import jeilm.api.app.invest.vo.InformationVO;

public interface InformationService {

	/**
	 * 포스트 리스트 조회
	 * @param informationVO
	 * @return
	 * @throws Exception
	 */
	List<InformationVO> selectPostList(InformationVO informationVO) throws Exception;
	
	/**
	 * 포스트 카운트 조회
	 * @param informationVO
	 * @return
	 * @throws Exception
	 */
	int selectPostCount(InformationVO informationVO) throws Exception;
	
}
