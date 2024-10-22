package jeilm.api.app.esg.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jeilm.api.app.esg.vo.EsgReportVO;

@Mapper
public interface EsgReportMapper {

	/**
	 * 운영현황 리스트 조회
	 * @param esgReportVO
	 * @return
	 * @throws Exception
	 */
	List<EsgReportVO> selectEsgReportList(EsgReportVO esgReportVO)  throws Exception;
	
}
