package jeilm.api.app.invest.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jeilm.api.app.invest.vo.DisclosureListVO;

@Mapper
public interface DisclosureMapper {

	/**
	 * 포스트 리스트 조회
	 * @param disclosureListVO
	 * @return
	 * @throws Exception
	 */
	List<DisclosureListVO> selectPostList(DisclosureListVO disclosureListVO) throws Exception;
	
	/**
	 * 포스트 카운트 조회
	 * @param disclosureListVO
	 * @return
	 * @throws Exception
	 */
	int selectPostCount(DisclosureListVO disclosureListVO) throws Exception;
}
