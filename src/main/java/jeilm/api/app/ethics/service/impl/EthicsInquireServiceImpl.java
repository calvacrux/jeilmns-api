package jeilm.api.app.ethics.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jeilm.api.app.ethics.service.EthicsInquireService;
import jeilm.api.app.ethics.service.mapper.EthicsInquireMapper;
import jeilm.api.app.ethics.vo.EthicsInquireVO;
import jeilm.api.app.file.service.StorageService;
import jeilm.api.app.file.vo.StorageVO;
import jeilm.api.app.mail.vo.MailAttachFileVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EthicsInquireServiceImpl implements EthicsInquireService {
	
	private final EthicsInquireMapper ethicsInquireMapper;
	private final StorageService storageService;

	@Override
	public List<MailAttachFileVO> selectEthicsInquireFileList(EthicsInquireVO ethicsInquireVO) throws Exception {
		return ethicsInquireMapper.selectEthicsInquireFileList(ethicsInquireVO);
	}

	@Override
	public int insertEthicsInquire(EthicsInquireVO ethicsInquireVO, List<MultipartFile> multipartFiles) throws Exception {
		int returnValue = 1;
		
		returnValue = ethicsInquireMapper.insertEthicsInquire(ethicsInquireVO);
		
		if (multipartFiles != null) {
			for (MultipartFile file : multipartFiles) {
				StorageVO storageVO = new StorageVO();
				storageVO.setFile_data_sn(ethicsInquireVO.getInquire_sn());
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
			ethicsInquireVO.setInquire_sn(ethicsInquireVO.getInquire_sn());
			ethicsInquireMapper.deleteEthicsInquire(ethicsInquireVO);
		}
		
		return returnValue;
	}

	@Override
	public String insertEthicsInquireReturnSn(EthicsInquireVO ethicsInquireVO, List<MultipartFile> multipartFiles) throws Exception {
		String retunSn = "";
		
		int returnValue = 1;
		
		returnValue = ethicsInquireMapper.insertEthicsInquire(ethicsInquireVO);
		
		if (multipartFiles != null) {
			for (MultipartFile file : multipartFiles) {
				StorageVO storageVO = new StorageVO();
				storageVO.setFile_data_sn(ethicsInquireVO.getInquire_sn());
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
			ethicsInquireVO.setInquire_sn(ethicsInquireVO.getInquire_sn());
			ethicsInquireMapper.deleteEthicsInquire(ethicsInquireVO);
		} else {
			retunSn = ethicsInquireVO.getInquire_sn();
		}
		
		return retunSn;
	}

	@Override
	public int deleteEthicsInquire(EthicsInquireVO ethicsInquireVO) throws Exception {
		return ethicsInquireMapper.deleteEthicsInquire(ethicsInquireVO);
	}

}
