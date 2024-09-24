package jeilm.api.app.invest.service;

import java.util.List;

import jeilm.api.app.invest.vo.BusinessVO;

public interface BusinessService {

	/**
	 * 포스트 리스트 조회
	 * @param businessVO
	 * @return
	 * @throws Exception
	 */
	List<BusinessVO> selectPostList(BusinessVO businessVO) throws Exception;

	/**
	 * 포스트 카운트 조회
	 * @param businessVO
	 * @return
	 * @throws Exception
	 */
	int selectPostCount(BusinessVO businessVO) throws Exception;
}
