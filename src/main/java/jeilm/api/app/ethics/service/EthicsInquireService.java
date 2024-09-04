package jeilm.api.app.ethics.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jeilm.api.app.ethics.vo.EthicsInquireVO;
import jeilm.api.app.mail.vo.MailAttachFileVO;

public interface EthicsInquireService {

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
	int insertEthicsInquire(EthicsInquireVO ethicsInquireVO, List<MultipartFile> multipartFiles) throws Exception;
	
	/**
	 * 윤리경영 문의현황 입력
	 * @param ethicsInquireVO
	 * @return
	 * @throws Exception
	 */
	String insertEthicsInquireReturnSn(EthicsInquireVO ethicsInquireVO, List<MultipartFile> multipartFiles) throws Exception;
	
	/**
	 * 윤리경영 문의현황 삭제
	 * @param ethicsInquireVO
	 * @return
	 * @throws Exception
	 */
	int deleteEthicsInquire(EthicsInquireVO ethicsInquireVO) throws Exception;
	
}
