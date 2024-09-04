package jeilm.api.app.file.service.impl;

import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jeilm.api.app.file.service.StorageService;
import jeilm.api.app.file.service.StorageUploadService;
import jeilm.api.app.file.service.mapper.StorageMapper;
import jeilm.api.app.file.vo.StorageVO;
import jeilm.api.cmm.util.StringUtil;

@Service
public class StorageServiceImpl implements StorageService {

	@Autowired
	private StorageMapper storageMapper;
	
	@Autowired
	private StorageUploadService storageUploadService;
	
	@Override
	public StorageVO selectFile(StorageVO storageVO) throws Exception {
		return storageMapper.selectFile(storageVO);
	}
	
	@Override
	public List<StorageVO> selectFileList(StorageVO storageVO) throws Exception {
		return storageMapper.selectFileList(storageVO);
	}
	
	@Override
	public StorageVO insertFile(StorageVO storageVO, MultipartFile multipartFile) throws Exception {
		int retrunValue = 0;
		
		Tika tika = new Tika();
		
		String tempName = UUID.randomUUID().toString().toLowerCase().replaceAll("-", "");
		
		storageVO.setFile_user_nm(FilenameUtils.getName(multipartFile.getOriginalFilename()));
		storageVO.setFile_server_nm(tempName + "." + FilenameUtils.getExtension(multipartFile.getOriginalFilename()));
		storageVO.setFile_path(storageVO.getSub_dir() + "/" + StringUtil.getDirFromUnixTime() + "/" + storageVO.getFile_server_nm());
		storageVO.setFile_mime(tika.detect(multipartFile.getInputStream()));
		storageVO.setFile_size(multipartFile.getSize());
		
		// Object Storage 에서 true or false 리턴 
		if (storageUploadService.uploadStorage(storageVO, multipartFile)) {
			retrunValue = storageMapper.insertFile(storageVO);
		}
		
		// 업로드 및 인서트 안되면 null 리턴
		if (retrunValue == 0) {
			storageVO = null;
		}
		
		return storageVO;
	}

	@Override
	public int updateFile(StorageVO storageVO, MultipartFile multipartFile) throws Exception {
		int retrunValue = 0;
		
		storageUploadService.deleteStorage(storageVO);
		storageVO = this.insertFile(storageVO, multipartFile);
		
		if (storageVO != null) {
			retrunValue = storageMapper.updateFile(storageVO);
		}
		
		return retrunValue;
	}

	@Override
	public int deleteFile(StorageVO storageVO) throws Exception {
		int retrunValue = 0;
		
		if (storageMapper.deleteFile(storageVO) > 0) {
			// Object Storage 에서 true or false 리턴 
			if(storageUploadService.deleteStorage(storageVO)) {
				retrunValue = 1;
			}
		}
		
		return retrunValue;
	}
	
	@Override
	public int updateFileDownCountPlus(StorageVO storageVO) throws Exception {
		return storageMapper.updateFileDownCountPlus(storageVO);
	}

}
