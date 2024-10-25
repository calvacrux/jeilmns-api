package jeilm.api.app.office.service;

import java.util.List;

import jeilm.api.app.office.vo.OfficeLocationVO;

public interface OfficeLocationService {

	/**
	 * 사업장 리스트
	 * @param officeLocationVO
	 * @return
	 * @throws Exception
	 */
	List<OfficeLocationVO> selectOfficeLocationList(OfficeLocationVO officeLocationVO) throws Exception;
	
}
