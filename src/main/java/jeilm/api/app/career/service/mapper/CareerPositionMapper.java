package jeilm.api.app.career.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jeilm.api.app.career.vo.CareerPositionVO;

@Mapper
public interface CareerPositionMapper {

	/**
	 * 채용공고 리스트
	 * @param careerPositionVO
	 * @return
	 * @throws Exception
	 */
	List<CareerPositionVO> selectPositionList(CareerPositionVO careerPositionVO) throws Exception;
	
}
