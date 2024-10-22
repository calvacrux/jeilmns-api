package jeilm.api.app.esg.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import jeilm.api.app.esg.service.EsgReportService;
import jeilm.api.app.esg.service.mapper.EsgReportMapper;
import jeilm.api.app.esg.vo.EsgReportVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EsgReportServiceImpl implements EsgReportService {
	
	private final EsgReportMapper esgReportMapper;

	@Override
	public List<EsgReportVO> selectEsgReportList(EsgReportVO esgReportVO) throws Exception {
		return esgReportMapper.selectEsgReportList(esgReportVO);
	}

}
