package jeilm.api.app.file.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jeilm.api.app.file.vo.StorageVO;

public interface StorageService {

	/**
	 * 파일 상세조회
	 * @param fileVO
	 * @return
	 * @throws Exception
	 */
	StorageVO selectFile(StorageVO storageVO) throws Exception;
	
	/**
	 * 파일 리스트조회
	 * @param fileVO
	 * @return
	 * @throws Exception
	 */
	List<StorageVO> selectFileList(StorageVO storageVO) throws Exception;
	
	/**
	 * 파일 입력
	 * @param storageVO
	 * @return
	 * @throws Exception
	 */
	StorageVO insertFile(StorageVO storageVO, MultipartFile multipartFile) throws Exception;
	
	/**
	 * 파일 수정
	 * @param storageVO
	 * @return
	 * @throws Exception
	 */
	int updateFile(StorageVO storageVO, MultipartFile multipartFile) throws Exception;
	
	/**
	 * 파일 삭제
	 * @param storageVO
	 * @return
	 * @throws Exception
	 */
	int deleteFile(StorageVO storageVO) throws Exception;
	
	/**
	 * 파일 다운로드 카운트 추가
	 * @param fileVO
	 * @return
	 * @throws Exception
	 */
	int updateFileDownCountPlus(StorageVO storageVO) throws Exception;
	
}
