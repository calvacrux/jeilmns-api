package jeilm.api.app.office.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import jeilm.api.app.office.service.OfficeNationService;
import jeilm.api.app.office.service.mapper.OfficeNationMapper;
import jeilm.api.app.office.vo.OfficeNationVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OfficeNationServiceImpl implements OfficeNationService {

	private final OfficeNationMapper officeNationMapper;
	
	@Override
	public List<OfficeNationVO> selectOfficeNationList(OfficeNationVO officeNationVO) throws Exception {
		return officeNationMapper.selectOfficeNationList(officeNationVO);
	}

}
