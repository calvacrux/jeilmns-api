package jeilm.api.app.board.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import jeilm.api.app.board.service.NewsBoardService;
import jeilm.api.app.board.service.mapper.NewsBoardMapper;
import jeilm.api.app.board.vo.NewsBoardDetailVO;
import jeilm.api.app.board.vo.NewsBoardListVO;
import jeilm.api.app.board.vo.NewsBoardNextVO;
import jeilm.api.app.board.vo.NewsBoardPrevVO;
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
	public NewsBoardPrevVO selectPostPrev(NewsBoardPrevVO newsBoardPrevVO) throws Exception {
		return newsBoardMapper.selectPostPrev(newsBoardPrevVO);
	}

	@Override
	public NewsBoardNextVO selectPostNext(NewsBoardNextVO newsBoardNextVO) throws Exception {
		return newsBoardMapper.selectPostNext(newsBoardNextVO);
	}

	@Override
	public List<NewsBoardListVO> selectPostList(NewsBoardListVO newsBoardListVO) throws Exception {
		return newsBoardMapper.selectPostList(newsBoardListVO);
	}
	
	@Override
	public List<NewsBoardListVO> selectPostListForMain(NewsBoardListVO newsBoardListVO) throws Exception {
		return newsBoardMapper.selectPostListForMain(newsBoardListVO);
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
