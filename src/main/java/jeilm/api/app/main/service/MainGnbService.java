package jeilm.api.app.main.service;

import java.util.List;

import jeilm.api.app.main.vo.MainGnbListVO;

public interface MainGnbService {

	/**
	 * 메인 GNB 리스트
	 * @param mainGnbListVO
	 * @return
	 * @throws Exception
	 */
	List<MainGnbListVO> selectMainGnbList(MainGnbListVO mainGnbListVO) throws Exception;  
	
}
