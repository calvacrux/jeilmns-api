package jeilm.api.app.office.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jeilm.api.app.office.vo.OfficeLocationVO;

@Mapper
public interface OfficeLocationMapper {

	/**
	 * 사업장 리스트
	 * @param officeLocationVO
	 * @return
	 * @throws Exception
	 */
	List<OfficeLocationVO> selectOfficeLocationList(OfficeLocationVO officeLocationVO) throws Exception;
	
}
