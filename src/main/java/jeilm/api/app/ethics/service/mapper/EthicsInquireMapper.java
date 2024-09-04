package jeilm.api.app.ethics.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jeilm.api.app.ethics.vo.EthicsInquireVO;
import jeilm.api.app.mail.vo.MailAttachFileVO;

@Mapper
public interface EthicsInquireMapper {

	/**
	 * 윤리경영 문의현황 파일 리스트
	 * @param ethicsInquireVO
	 * @return
	 * @throws Exception
	 */
	List<MailAttachFileVO> selectEthicsInquireFileList(EthicsInquireVO ethicsInquireVO) throws Exception; 
	
	/**
	 * 윤리경영 문의현황 입력
	 * @param ethicsInquireVO
	 * @return
	 * @throws Exception
	 */
	int insertEthicsInquire(EthicsInquireVO ethicsInquireVO) throws Exception;
	
	/**
	 * 윤리경영 문의현황 삭제
	 * @param ethicsInquireVO
	 * @return
	 * @throws Exception
	 */
	int deleteEthicsInquire(EthicsInquireVO ethicsInquireVO) throws Exception;
	
}
