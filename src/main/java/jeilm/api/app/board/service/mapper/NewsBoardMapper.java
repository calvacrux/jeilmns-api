package jeilm.api.app.board.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jeilm.api.app.board.vo.NewsBoardDetailVO;
import jeilm.api.app.board.vo.NewsBoardListVO;

@Mapper
public interface NewsBoardMapper {
	
	/**
	 * 포스트 상세 조회
	 * @param newsBoardDetailVO
	 * @return
	 * @throws Exception
	 */
	NewsBoardDetailVO selectPost(NewsBoardDetailVO newsBoardDetailVO) throws Exception;
	
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
