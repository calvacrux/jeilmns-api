package jeilm.api.app.esg.service;

import java.util.List;

import jeilm.api.app.esg.vo.EsgCodeVO;

public interface EsgCodeService {

	/**
	 * 포스트 리스트 조회
	 * @param esgCodeVO
	 * @return
	 * @throws Exception
	 */
	List<EsgCodeVO> selectPostList(EsgCodeVO esgCodeVO) throws Exception;
	
	/**
	 * 포스트 카운트 조회
	 * @param esgCodeVO
	 * @return
	 * @throws Exception
	 */
	int selectPostCount(EsgCodeVO esgCodeVO) throws Exception;
	
	/**
	 * 윤리강령 정책서 파일
	 * @param esgCodeVO
	 * @return
	 * @throws Exception
	 */
	EsgCodeVO selectTopFile(EsgCodeVO esgCodeVO) throws Exception;
}
