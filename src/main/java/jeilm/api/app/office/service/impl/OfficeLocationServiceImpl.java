package jeilm.api.app.office.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import jeilm.api.app.office.service.OfficeLocationService;
import jeilm.api.app.office.service.mapper.OfficeLocationMapper;
import jeilm.api.app.office.vo.OfficeLocationVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OfficeLocationServiceImpl implements OfficeLocationService {

	private final OfficeLocationMapper officeLocationMapper;
	
	@Override
	public List<OfficeLocationVO> selectOfficeLocationList(OfficeLocationVO officeLocationVO) throws Exception {
		return officeLocationMapper.selectOfficeLocationList(officeLocationVO);
	}

}
