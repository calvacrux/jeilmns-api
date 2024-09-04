package jeilm.api.app.support.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jeilm.api.app.support.vo.SupportReceiveVO;

@Mapper
public interface SupportReceiveMapper {

	/**
	 * 고객지원 메일수신자 리스트
	 * @param supportReceiveVO
	 * @return
	 * @throws Exception
	 */
	List<SupportReceiveVO> selectSupportReceiveList(SupportReceiveVO supportReceiveVO) throws Exception;
	
}
