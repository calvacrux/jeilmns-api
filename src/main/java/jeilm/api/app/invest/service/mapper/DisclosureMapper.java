package jeilm.api.app.invest.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jeilm.api.app.invest.vo.DisclosureVO;

@Mapper
public interface DisclosureMapper {

	/**
	 * 포스트 리스트 조회
	 * @param disclosureVO
	 * @return
	 * @throws Exception
	 */
	List<DisclosureVO> selectPostList(DisclosureVO disclosureVO) throws Exception;
	
	/**
	 * 포스트 카운트 조회
	 * @param disclosureVO
	 * @return
	 * @throws Exception
	 */
	int selectPostCount(DisclosureVO disclosureVO) throws Exception;
}
