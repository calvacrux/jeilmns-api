package jeilm.api.app.main.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import jeilm.api.app.main.service.MainGnbService;
import jeilm.api.app.main.service.mapper.MainGnbMapper;
import jeilm.api.app.main.vo.MainGnbListVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MainGnbServiceImpl implements MainGnbService {

	private final MainGnbMapper mainGnbMapper;
	
	@Override
	public List<MainGnbListVO> selectMainGnbList(MainGnbListVO mainGnbListVO) throws Exception {
		return mainGnbMapper.selectMainGnbList(mainGnbListVO);
	}

}
