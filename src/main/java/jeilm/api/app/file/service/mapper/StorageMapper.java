package jeilm.api.app.file.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jeilm.api.app.file.vo.StorageVO;

@Mapper
public interface StorageMapper {

	/**
	 * 파일 상세조회
	 * @param storageVO
	 * @return
	 * @throws Exception
	 */
	StorageVO selectFile(StorageVO storageVO) throws Exception;
	
	/**
	 * 파일 리스트조회
	 * @param storageVO
	 * @return
	 * @throws Exception
	 */
	List<StorageVO> selectFileList(StorageVO storageVO) throws Exception;
	
	/**
	 * 파일 카운트 - 연관 데이터
	 * @param storageVO
	 * @return
	 * @throws Exception
	 */
	int selectFileCountByDataSn(StorageVO storageVO) throws Exception;
	
	/**
	 * 파일 입력
	 * @param storageVO
	 * @return
	 * @throws Exception
	 */
	int insertFile(StorageVO storageVO) throws Exception;
	
	/**
	 * 파일 수정
	 * @param storageVO
	 * @return
	 * @throws Exception
	 */
	int updateFile(StorageVO storageVO) throws Exception;
	
	/**
	 * 파일 삭제
	 * @param storageVO
	 * @return
	 * @throws Exception
	 */
	int deleteFile(StorageVO storageVO) throws Exception;
	
	/**
	 * 파일 삭제
	 * @param storageVO
	 * @return
	 * @throws Exception
	 */
	int deleteFileByDataSn(StorageVO storageVO) throws Exception;
	
	/**
	 * 파일 다운로드 카운트 추가
	 * @param storageVO
	 * @return
	 * @throws Exception
	 */
	int updateFileDownCountPlus(StorageVO storageVO) throws Exception;
}
