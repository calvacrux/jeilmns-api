package jeilm.api.app.invest.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jeilm.api.app.invest.vo.BusinessVO;

@Mapper
public interface BusinessMapper {

	/**
	 * 포스트 리스트 조회
	 * @param businessVO
	 * @return
	 * @throws Exception
	 */
	List<BusinessVO> selectPostList(BusinessVO businessVO) throws Exception;

	/**
	 * 포스트 카운트 조회
	 * @param businessVO
	 * @return
	 * @throws Exception
	 */
	int selectPostCount(BusinessVO businessVO) throws Exception;
	
}
