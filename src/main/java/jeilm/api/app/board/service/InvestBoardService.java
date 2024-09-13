package jeilm.api.app.board.service;

import java.util.List;

import jeilm.api.app.board.vo.InvestBoardListVO;
import jeilm.api.app.board.vo.InvestBoardDetailVO;

public interface InvestBoardService {

	/**
	 * 포스트 상세 조회
	 * @param investBoardDetailVO
	 * @return
	 * @throws Exception
	 */
	InvestBoardDetailVO selectPost(InvestBoardDetailVO investBoardDetailVO) throws Exception;
	
	/**
	 * 포스트 리스트 조회
	 * @param investBoardListVO
	 * @return
	 * @throws Exception
	 */
	List<InvestBoardListVO> selectPostList(InvestBoardListVO investBoardListVO) throws Exception;
	
	/**
	 * 포스트 카운트 조회
	 * @param investBoardListVO
	 * @return
	 * @throws Exception
	 */
	int selectPostCount(InvestBoardListVO investBoardListVO) throws Exception;
	
	/**
	 * 포스트 조회카운트 증가
	 * @param investBoardDetailVO
	 * @return
	 * @throws Exception
	 */
	int updateViewCount(InvestBoardDetailVO investBoardDetailVO) throws Exception;
	
}
