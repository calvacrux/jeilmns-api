package jeilm.api.app.support.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jeilm.api.app.support.vo.SupportContactVO;

@Mapper
public interface SupportContactMapper {
	
	/**
	 * 연락처 리스트 조회
	 * @param supportContactVO
	 * @return
	 * @throws Exception
	 */
	List<SupportContactVO> selectSupportContactList(SupportContactVO supportContactVO)  throws Exception;
	
}
