package jeilm.api.app.career.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jeilm.api.app.career.vo.CareerApplyVO;
import jeilm.api.app.mail.vo.MailAttachFileVO;

@Mapper
public interface CareerApplyMapper {
	
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
	int insertCareerApply(CareerApplyVO careerApplyVO) throws Exception;
	
	/**
	 * 채용지원 삭제
	 * @param careerApplyVO
	 * @return
	 * @throws Exception
	 */
	int deleteCareerApply(CareerApplyVO careerApplyVO) throws Exception;
		
}
