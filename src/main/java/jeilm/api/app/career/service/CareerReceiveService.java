package jeilm.api.app.career.service;

import java.util.List;

import jeilm.api.app.career.vo.CareerReceiveVO;

public interface CareerReceiveService {

	/**
	 * 인재채용 수신메일 리스트 조회
	 * @param receiveVO
	 * @return
	 * @throws Exception
	 */
	List<CareerReceiveVO> selectReceiveList(CareerReceiveVO careerReceiveVO) throws Exception;
	
}
