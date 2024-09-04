package jeilm.api.app.file.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jeilm.api.app.file.service.StorageService;
import jeilm.api.app.file.service.StorageUploadService;
import jeilm.api.app.file.vo.StorageVO;

@Controller
public class StorageController {
	
	@Autowired
	private StorageService storageService;
	
	@Autowired
	private StorageUploadService storageUploadService;
	
	@GetMapping("/util/storage/download")
	private void storageDownload(@RequestParam String file_sn, HttpServletRequest request, HttpServletResponse response) throws Exception {
		StorageVO storageVO = new StorageVO();
		storageVO.setFile_sn(file_sn);
		storageVO = storageService.selectFile(storageVO);
		
		//파일 다운로드
		storageUploadService.downloadStorageByPath(storageVO.getFile_path(), storageVO.getFile_user_nm(), request, response);
		
		//파일 다운카운트증가
		storageService.updateFileDownCountPlus(storageVO);
	}
	
}
