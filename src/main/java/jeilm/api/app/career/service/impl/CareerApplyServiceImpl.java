package jeilm.api.app.career.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jeilm.api.app.career.service.CareerApplyService;
import jeilm.api.app.career.service.mapper.CareerApplyMapper;
import jeilm.api.app.career.vo.CareerApplyVO;
import jeilm.api.app.file.service.StorageService;
import jeilm.api.app.file.vo.StorageVO;
import jeilm.api.app.mail.vo.MailAttachFileVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CareerApplyServiceImpl implements CareerApplyService {
	
	private final CareerApplyMapper careerApplyMapper;
	private final StorageService storageService;
	
	@Override
	public List<MailAttachFileVO> selectCareerApplyFileList(CareerApplyVO careerApplyVO) throws Exception {
		return careerApplyMapper.selectCareerApplyFileList(careerApplyVO);
	}

	@Override
	public int insertCareerApply(CareerApplyVO careerApplyVO, MultipartFile multipartFileProfile, MultipartFile multipartFilePortfolio) throws Exception {
		int returnValue = 1;
		
		returnValue = careerApplyMapper.insertCareerApply(careerApplyVO);
		
		// 파일 추가
		if (returnValue > 0) {
			if (multipartFileProfile != null) {
				if (!multipartFileProfile.isEmpty()) {
					StorageVO storageVO = new StorageVO();
					storageVO.setFile_data_sn(careerApplyVO.getApply_sn());
					storageVO.setReg_sn("");
					storageVO.setSub_dir("career");
					storageVO.setFile_content_cd("profile");
					storageVO = storageService.insertFile(storageVO, multipartFileProfile);	
					
					if (storageVO == null) {
						returnValue = 0;
					}
				}
			}
			
			if (multipartFilePortfolio != null) {
				if (!multipartFilePortfolio.isEmpty()) {
					StorageVO storageVO = new StorageVO();
					storageVO.setFile_data_sn(careerApplyVO.getApply_sn());
					storageVO.setReg_sn("");
					storageVO.setSub_dir("career");
					storageVO.setFile_content_cd("portfolio");
					storageVO = storageService.insertFile(storageVO, multipartFilePortfolio);	
					
					if (storageVO == null) {
						returnValue = 0;
					}
				}
			}
			
		}
		
		// 파일 업로드 오류시 게시물 삭제
		if (returnValue < 1) {
			careerApplyVO.setApply_sn(careerApplyVO.getApply_sn());
			careerApplyMapper.deleteCareerApply(careerApplyVO);
		}
		
		return returnValue;
	}
	
	@Override
	public String insertCareerApplyReturnSn(CareerApplyVO careerApplyVO, MultipartFile multipartFileProfile, MultipartFile multipartFilePortfolio) throws Exception {
		String retunSn = "";
		
		int returnValue = 1;
		
		returnValue = careerApplyMapper.insertCareerApply(careerApplyVO);
		
		// 파일 추가
		if (returnValue > 0) {
			if (multipartFileProfile != null) {
				if (!multipartFileProfile.isEmpty()) {
					StorageVO storageVO = new StorageVO();
					storageVO.setFile_data_sn(careerApplyVO.getApply_sn());
					storageVO.setReg_sn("");
					storageVO.setSub_dir("career");
					storageVO.setFile_content_cd("profile");
					storageVO = storageService.insertFile(storageVO, multipartFileProfile);	
					
					if (storageVO == null) {
						returnValue = 0;
					}
				}
			}
			
			if (multipartFilePortfolio != null) {
				if (!multipartFilePortfolio.isEmpty()) {
					StorageVO storageVO = new StorageVO();
					storageVO.setFile_data_sn(careerApplyVO.getApply_sn());
					storageVO.setReg_sn("");
					storageVO.setSub_dir("career");
					storageVO.setFile_content_cd("portfolio");
					storageVO = storageService.insertFile(storageVO, multipartFilePortfolio);	
					
					if (storageVO == null) {
						returnValue = 0;
					}
				}
			}
			
		}
		
		// 파일 업로드 오류시 게시물 삭제
		if (returnValue < 1) {
			careerApplyVO.setApply_sn(careerApplyVO.getApply_sn());
			careerApplyMapper.deleteCareerApply(careerApplyVO);
		} else {
			retunSn = careerApplyVO.getApply_sn();
		}
		
		return retunSn;
	}
	
	@Override
	public int deleteCareerApply(CareerApplyVO careerApplyVO) throws Exception {
		return careerApplyMapper.deleteCareerApply(careerApplyVO);
	}

}
