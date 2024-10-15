package jeilm.api.app.board.service;

import java.util.List;

import jeilm.api.app.board.vo.NewsBoardDetailVO;
import jeilm.api.app.board.vo.NewsBoardListVO;
import jeilm.api.app.board.vo.NewsBoardNextVO;
import jeilm.api.app.board.vo.NewsBoardPrevVO;

public interface NewsBoardService {

	/**
	 * 포스트 상세 조회
	 * @param newsBoardDetailVO
	 * @return
	 * @throws Exception
	 */
	NewsBoardDetailVO selectPost(NewsBoardDetailVO newsBoardDetailVO) throws Exception;
	
	/**
	 * 포스트 상세 조회 - 이전
	 * @param newsBoardPrevVO
	 * @return
	 * @throws Exception
	 */
	NewsBoardPrevVO selectPostPrev(NewsBoardPrevVO newsBoardPrevVO) throws Exception;
	
	/**
	 * 포스트 상세 조회 - 다음
	 * @param newsBoardNextVO
	 * @return
	 * @throws Exception
	 */
	NewsBoardNextVO selectPostNext(NewsBoardNextVO newsBoardNextVO) throws Exception;
	
	/**
	 * 포스트 리스트 조회
	 * @param newsBoardListVO
	 * @return
	 * @throws Exception
	 */
	List<NewsBoardListVO> selectPostList(NewsBoardListVO newsBoardListVO) throws Exception;
	
	/**
	 * 포스트 카운트 조회
	 * @param newsBoardListVO
	 * @return
	 * @throws Exception
	 */
	int selectPostCount(NewsBoardListVO newsBoardListVO) throws Exception;
	
	/**
	 * 포스트 조회카운트 증가
	 * @param newsBoardDetailVO
	 * @return
	 * @throws Exception
	 */
	int updateViewCount(NewsBoardDetailVO newsBoardDetailVO) throws Exception;
	
}
