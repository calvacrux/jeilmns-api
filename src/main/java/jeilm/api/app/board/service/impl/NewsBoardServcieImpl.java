package jeilm.api.app.board.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import jeilm.api.app.board.service.NewsBoardService;
import jeilm.api.app.board.service.mapper.NewsBoardMapper;
import jeilm.api.app.board.vo.NewsBoardDetailVO;
import jeilm.api.app.board.vo.NewsBoardListVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NewsBoardServcieImpl implements NewsBoardService {
	
	private final NewsBoardMapper newsBoardMapper;

	@Override
	public NewsBoardDetailVO selectPost(NewsBoardDetailVO newsBoardDetailVO) throws Exception {
		return newsBoardMapper.selectPost(newsBoardDetailVO);
	}

	@Override
	public List<NewsBoardListVO> selectPostList(NewsBoardListVO newsBoardListVO) throws Exception {
		return newsBoardMapper.selectPostList(newsBoardListVO);
	}

	@Override
	public int selectPostCount(NewsBoardListVO newsBoardListVO) throws Exception {
		return newsBoardMapper.selectPostCount(newsBoardListVO);
	}

	@Override
	public int updateViewCount(NewsBoardDetailVO newsBoardDetailVO) throws Exception {
		return newsBoardMapper.updateViewCount(newsBoardDetailVO);
	}

}
