package jeilm.api.app.office.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jeilm.api.app.office.vo.OfficeNationVO;

@Mapper
public interface OfficeNationMapper {

	/**
	 * 국가 리스트 조회
	 * @param officeNationVO
	 * @return
	 * @throws Exception
	 */
	List<OfficeNationVO> selectOfficeNationList(OfficeNationVO officeNationVO)  throws Exception;
	
}
