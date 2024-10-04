package jeilm.api.app.career.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jeilm.api.app.career.vo.CareerApplyVO;
import jeilm.api.app.mail.vo.MailAttachFileVO;

public interface CareerApplyService {
	
	/**
	 * 채용지원 파일 리스트
	 * @param careerApplyVO
	 * @return
	 * @throws Exception
	 */
	List<MailAttachFileVO> selectCareerApplyFileList(CareerApplyVO careerApplyVO) throws Exception; 
	
	/**
	 * 채용지원 입력
	 * @param careerApplyVO
	 * @param multipartFiles
	 * @return
	 * @throws Exception
	 */
	int insertCareerApply(CareerApplyVO careerApplyVO, MultipartFile multipartFileProfile, MultipartFile multipartFilePortfolio) throws Exception;
	
	/**
	 * 채용지원 입력
	 * @param careerApplyVO
	 * @param multipartFiles
	 * @return
	 * @throws Exception
	 */
	String insertCareerApplyReturnSn(CareerApplyVO careerApplyVO, MultipartFile multipartFileProfile, MultipartFile multipartFilePortfolio) throws Exception;
	
	/**
	 * 채용지원 삭제
	 * @param careerApplyVO
	 * @return
	 * @throws Exception
	 */
	int deleteCareerApply(CareerApplyVO careerApplyVO) throws Exception;
	
}
