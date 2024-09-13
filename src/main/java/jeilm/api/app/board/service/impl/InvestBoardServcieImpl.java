package jeilm.api.app.board.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import jeilm.api.app.board.service.InvestBoardService;
import jeilm.api.app.board.service.mapper.InvestBoardMapper;
import jeilm.api.app.board.vo.InvestBoardDetailVO;
import jeilm.api.app.board.vo.InvestBoardListVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InvestBoardServcieImpl implements InvestBoardService {
	
	private final InvestBoardMapper investBoardMapper;

	@Override
	public InvestBoardDetailVO selectPost(InvestBoardDetailVO investBoardDetailVO) throws Exception {
		return investBoardMapper.selectPost(investBoardDetailVO);
	}

	@Override
	public List<InvestBoardListVO> selectPostList(InvestBoardListVO investBoardListVO) throws Exception {
		return investBoardMapper.selectPostList(investBoardListVO);
	}

	@Override
	public int selectPostCount(InvestBoardListVO investBoardListVO) throws Exception {
		return investBoardMapper.selectPostCount(investBoardListVO);
	}

	@Override
	public int updateViewCount(InvestBoardDetailVO investBoardDetailVO) throws Exception {
		return investBoardMapper.updateViewCount(investBoardDetailVO);
	}

}
