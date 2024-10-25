package jeilm.api.app.office.service;

import java.util.List;

import jeilm.api.app.office.vo.OfficeNationVO;

public interface OfficeNationService {

	/**
	 * 국가 리스트 조회
	 * @param officeNationVO
	 * @return
	 * @throws Exception
	 */
	List<OfficeNationVO> selectOfficeNationList(OfficeNationVO officeNationVO)  throws Exception;
	
}
