package jeilm.api.app.career.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import jeilm.api.app.career.service.CareerReceiveService;
import jeilm.api.app.career.service.mapper.CareerReceiveMapper;
import jeilm.api.app.career.vo.CareerReceiveVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CareerReceiveServiceImpl implements CareerReceiveService {

	private final CareerReceiveMapper receiveMapper;
	
	@Override
	public List<CareerReceiveVO> selectReceiveList(CareerReceiveVO careerReceiveVO) throws Exception {
		return receiveMapper.selectReceiveList(careerReceiveVO);
	}
	
}
