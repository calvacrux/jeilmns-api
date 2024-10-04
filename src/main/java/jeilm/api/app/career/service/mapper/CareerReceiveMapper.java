package jeilm.api.app.career.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jeilm.api.app.career.vo.CareerReceiveVO;

@Mapper
public interface CareerReceiveMapper {

	/**
	 * 인재채용 수신메일 리스트 조회
	 * @param receiveVO
	 * @return
	 * @throws Exception
	 */
	List<CareerReceiveVO> selectReceiveList(CareerReceiveVO careerReceiveVO) throws Exception;
	
}
