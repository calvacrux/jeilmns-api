package jeilm.api.app.support.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jeilm.api.app.mail.vo.MailAttachFileVO;
import jeilm.api.app.support.vo.SupportInquireVO;

public interface SupportInquireService {

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
	int insertSupportInquire(SupportInquireVO supportInquireVO, List<MultipartFile> multipartFiles) throws Exception;
	
	/**
	 * 고객지원 문의현황 입력
	 * @param supportInquireVO
	 * @return
	 * @throws Exception
	 */
	String insertSupportInquireReturnSn(SupportInquireVO supportInquireVO, List<MultipartFile> multipartFiles) throws Exception;
	
	/**
	 * 고객지원 문의현황 삭제
	 * @param supportInquireVO
	 * @return
	 * @throws Exception
	 */
	int deleteSupportInquire(SupportInquireVO supportInquireVO) throws Exception;
	
}
