package jeilm.api.app.board.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jeilm.api.app.board.vo.InvestBoardDetailVO;
import jeilm.api.app.board.vo.InvestBoardListVO;

@Mapper
public interface InvestBoardMapper {
	
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
