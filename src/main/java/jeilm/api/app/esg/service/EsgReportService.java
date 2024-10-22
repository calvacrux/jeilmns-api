package jeilm.api.app.esg.service;

import java.util.List;

import jeilm.api.app.esg.vo.EsgReportVO;

public interface EsgReportService {

	/**
	 * 운영현황 리스트 조회
	 * @param esgReportVO
	 * @return
	 * @throws Exception
	 */
	List<EsgReportVO> selectEsgReportList(EsgReportVO esgReportVO)  throws Exception;
	
}
