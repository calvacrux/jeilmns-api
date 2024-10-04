package jeilm.api.app.career.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import jeilm.api.app.career.service.CareerPositionService;
import jeilm.api.app.career.service.mapper.CareerPositionMapper;
import jeilm.api.app.career.vo.CareerPositionVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CareerPositionServiceImpl implements CareerPositionService {
	
	private final CareerPositionMapper careerPositionMapper;

	@Override
	public CareerPositionVO selectPosition(CareerPositionVO careerPositionVO) throws Exception {
		return careerPositionMapper.selectPosition(careerPositionVO);
	}
	
	@Override
	public List<CareerPositionVO> selectPositionList(CareerPositionVO careerPositionVO) throws Exception {
		return careerPositionMapper.selectPositionList(careerPositionVO);
	}

}
