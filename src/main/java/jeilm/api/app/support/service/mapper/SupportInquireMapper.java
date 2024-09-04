package jeilm.api.app.support.service.mapper;

import org.apache.ibatis.annotations.Mapper;

import jeilm.api.app.support.vo.SupportInquireVO;

@Mapper
public interface SupportInquireMapper {

	/**
	 * 고객지원 문의현황 입력
	 * @param supportInquireVO
	 * @return
	 * @throws Exception
	 */
	int insertSupportInquire(SupportInquireVO supportInquireVO) throws Exception;
	
}
