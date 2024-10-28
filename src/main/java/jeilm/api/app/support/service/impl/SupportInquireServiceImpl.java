package jeilm.api.app.support.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jeilm.api.app.file.service.StorageService;
import jeilm.api.app.file.vo.StorageVO;
import jeilm.api.app.mail.vo.MailAttachFileVO;
import jeilm.api.app.support.service.SupportInquireService;
import jeilm.api.app.support.service.mapper.SupportInquireMapper;
import jeilm.api.app.support.vo.SupportInquireVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupportInquireServiceImpl implements SupportInquireService {
	
	private final SupportInquireMapper supportInquireMapper;
	private final StorageService storageService;

	@Override
	public List<MailAttachFileVO> selectSupportInquireFileList(SupportInquireVO supportInquireVO) throws Exception {
		return supportInquireMapper.selectSupportInquireFileList(supportInquireVO);
	}
	
	@Override
	public int insertSupportInquire(SupportInquireVO supportInquireVO, List<MultipartFile> multipartFiles) throws Exception {
		int returnValue = 1;
		
		returnValue = supportInquireMapper.insertSupportInquire(supportInquireVO);
		
		if (multipartFiles != null) {
			for (MultipartFile file : multipartFiles) {
				StorageVO storageVO = new StorageVO();
				storageVO.setFile_data_sn(supportInquireVO.getInquire_sn());
				storageVO.setReg_sn("");
				storageVO.setSub_dir("inquire");
				
				if (!file.isEmpty()) {
					// 파일 컨텐츠 코드별 추가 - 인덱스와 컨텐츠 확인필요				
					if (multipartFiles.indexOf(file) == 0) {
						storageVO.setFile_content_cd("main");
					}
					
					storageVO = storageService.insertFile(storageVO, file);
					
					if (storageVO == null) {
						returnValue = 0;
					}
				}
			}
		}
		
		// 파일 업로드 오류시 게시물 삭제
		if (returnValue < 1) {
			supportInquireVO.setInquire_sn(supportInquireVO.getInquire_sn());
			supportInquireMapper.deleteSupportInquire(supportInquireVO);
		}
		
		return returnValue;
	}

	@Override
	public String insertSupportInquireReturnSn(SupportInquireVO supportInquireVO, List<MultipartFile> multipartFiles) throws Exception {
		String retunSn = "";
		
		int returnValue = 1;
		
		returnValue = supportInquireMapper.insertSupportInquire(supportInquireVO);
		
		if (multipartFiles != null) {
			for (MultipartFile file : multipartFiles) {
				StorageVO storageVO = new StorageVO();
				storageVO.setFile_data_sn(supportInquireVO.getInquire_sn());
				storageVO.setReg_sn("");
				storageVO.setSub_dir("ethics");
				
				if (!file.isEmpty()) {
					// 파일 컨텐츠 코드별 추가 - 인덱스와 컨텐츠 확인필요				
					if (multipartFiles.indexOf(file) == 0) {
						storageVO.setFile_content_cd("main");
					}
					
					storageVO = storageService.insertFile(storageVO, file);
					
					if (storageVO == null) {
						returnValue = 0;
					}
				}
			}
		}
		
		// 파일 업로드 오류시 게시물 삭제
		if (returnValue < 1) {
			supportInquireVO.setInquire_sn(supportInquireVO.getInquire_sn());
			supportInquireMapper.deleteSupportInquire(supportInquireVO);
		} else {
			retunSn = supportInquireVO.getInquire_sn();
		}
		
		return retunSn;
	}

	@Override
	public int deleteSupportInquire(SupportInquireVO supportInquireVO) throws Exception {
		return supportInquireMapper.deleteSupportInquire(supportInquireVO);
	}

}
