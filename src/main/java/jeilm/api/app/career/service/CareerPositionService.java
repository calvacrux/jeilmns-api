package jeilm.api.app.career.service;

import java.util.List;

import jeilm.api.app.career.vo.CareerPositionVO;

public interface CareerPositionService {
	
	/**
	 * 채용공고 조회
	 * @param careerPositionVO
	 * @return
	 * @throws Exception
	 */
	CareerPositionVO selectPosition(CareerPositionVO careerPositionVO) throws Exception;
	
	/**
	 * 채용공고 리스트
	 * @param careerPositionVO
	 * @return
	 * @throws Exception
	 */
	List<CareerPositionVO> selectPositionList(CareerPositionVO careerPositionVO) throws Exception;
	
}
