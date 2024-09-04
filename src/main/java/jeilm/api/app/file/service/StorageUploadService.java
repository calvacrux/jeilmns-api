package jeilm.api.app.file.service;

import java.io.BufferedInputStream;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jeilm.api.app.file.vo.StorageVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StorageUploadService {

	@Value("${global.ncloud.storage.bucket.name}")
	private String bucketName;
	
	@Value("${global.ncloud.storage.path}")
	private String ncloudPath;
	
	@Resource
	private AmazonS3 amazonS3;
	
	/**
	 * Ncolud 파일 업로드
	 * @param multipartFile
	 * @param subDirectory
	 * @return
	 * @throws Exception
	 */
	public boolean uploadStorage(StorageVO storageVO, MultipartFile multipartFile) throws Exception {
		boolean rerunValue = false;
		
		if (multipartFile.isEmpty()) {
			rerunValue = true;
		} else {
			ObjectMetadata objectMetadata = new ObjectMetadata();
			objectMetadata.setContentLength(storageVO.getFile_size());
			objectMetadata.setContentType(storageVO.getFile_mime());
	        
	        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, storageVO.getFile_path(), multipartFile.getInputStream(), objectMetadata);
	        putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);
	        
	        try {
	        	amazonS3.putObject(putObjectRequest);
	        	rerunValue = true;
	        } catch (AmazonS3Exception e) {
	        	log.error("Error StorageUploadService : " + e.getMessage());
			}
		}
        
        return rerunValue;
	}
	
	/**
	 * Ncolud 파일 삭제
	 * @param storageVO
	 * @return
	 * @throws Exception
	 */
	public boolean deleteStorage(StorageVO storageVO) throws Exception {
		boolean rerunValue = false;
		
		DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(bucketName, storageVO.getFile_path());
		
		try {
        	amazonS3.deleteObject(deleteObjectRequest);
        	rerunValue = true;
        } catch (AmazonS3Exception e) {
        	log.error("Error StorageDeleteService : " + e.getMessage());
		}
		
		return rerunValue;
	}
	
	/**
	 * Ncolud 파일 다운로드 - 파일 경로로
	 * @param storagePath
	 * @throws Exception
	 */
	public void downloadStorageByPath(String file_path, String file_user_name, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!StringUtils.isEmpty(file_path) && !StringUtils.isEmpty(file_user_name)) {
			String storageUrl = ncloudPath + file_path;
			
			//접속 브라우저 확인
			String header = request.getHeader("User-Agent");
		    String fileName = "";
		    
		    if ((header.contains("MSIE")) || (header.contains("Trident")) || (header.contains("Edge"))) {
		        fileName = URLEncoder.encode(file_user_name, "UTF-8");
		    } else {
		        fileName = new String(file_user_name.getBytes("UTF-8"), "iso-8859-1");
		    }
		    
		    response.setContentType("application/octet-stream");
		    response.setHeader("Content-Disposition", "attachment; filename=\""+ fileName + "\"");
		    
		    BufferedInputStream in = new BufferedInputStream(new URL(storageUrl).openStream());
		    
		    FileCopyUtils.copy(in, response.getOutputStream());
		    in.close();
		    response.getOutputStream().flush();
		    response.getOutputStream().close();
		}
	}
}
