package jeilm.api.app.support.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jeilm.api.app.mail.vo.MailAttachFileVO;
import jeilm.api.app.support.vo.SupportInquireVO;

@Mapper
public interface SupportInquireMapper {

	/**
	 * 고객지원 문의현황 파일 리스트
	 * @param supportInquireVO
	 * @return
	 * @throws Exception
	 */
	List<MailAttachFileVO> selectSupportInquireFileList(SupportInquireVO supportInquireVO) throws Exception;
	
	/**
	 * 고객지원 문의현황 입력
	 * @param supportInquireVO
	 * @return
	 * @throws Exception
	 */
	int insertSupportInquire(SupportInquireVO supportInquireVO) throws Exception;
	
	/**
	 * 고객지원 문의현황 삭제
	 * @param supportInquireVO
	 * @return
	 * @throws Exception
	 */
	int deleteSupportInquire(SupportInquireVO supportInquireVO) throws Exception;
	
}
