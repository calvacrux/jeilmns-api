package jeilm.api.app.ethics.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jeilm.api.app.ethics.vo.EthicsCodeVO;
import jeilm.api.app.file.vo.DownFileVO;

@Mapper
public interface EthicsCodeMapper {

	/**
	 * 포스트 리스트 조회
	 * @param ethicsCodeVO
	 * @return
	 * @throws Exception
	 */
	List<EthicsCodeVO> selectPostList(EthicsCodeVO ethicsCodeVO) throws Exception;
	
	/**
	 * 포스트 카운트 조회
	 * @param ethicsCodeVO
	 * @return
	 * @throws Exception
	 */
	int selectPostCount(EthicsCodeVO ethicsCodeVO) throws Exception;

	/**
	 * 윤리강령 정책서 파일
	 * @param ethicsCodeVO
	 * @return
	 * @throws Exception
	 */
	DownFileVO selectTopFile(EthicsCodeVO ethicsCodeVO) throws Exception;
	
}
