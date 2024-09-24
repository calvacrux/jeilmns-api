package jeilm.api.app.invest.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jeilm.api.app.invest.vo.InformationVO;

@Mapper
public interface InformationMapper {

	/**
	 * 포스트 리스트 조회
	 * @param informationVO
	 * @return
	 * @throws Exception
	 */
	List<InformationVO> selectPostList(InformationVO informationVO) throws Exception;
	
	/**
	 * 포스트 카운트 조회
	 * @param informationVO
	 * @return
	 * @throws Exception
	 */
	int selectPostCount(InformationVO informationVO) throws Exception;
	
}
