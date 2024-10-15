package jeilm.api.app.invest.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jeilm.api.app.invest.vo.DisclosureDetailVO;
import jeilm.api.app.invest.vo.DisclosureListVO;
import jeilm.api.app.invest.vo.DisclosureNextVO;
import jeilm.api.app.invest.vo.DisclosurePrevVO;

@Mapper
public interface DisclosureMapper {

	/**
	 * 포스트 상세 조회
	 * @param disclosureDetailVO
	 * @return
	 * @throws Exception
	 */
	DisclosureDetailVO selectPost(DisclosureDetailVO disclosureDetailVO) throws Exception;
	
	/**
	 * 포스트 상세 조회 - 이전
	 * @param disclosurePrevVO
	 * @return
	 * @throws Exception
	 */
	DisclosurePrevVO selectPostPrev(DisclosurePrevVO disclosurePrevVO) throws Exception;
	
	/**
	 * 포스트 상세 조회 - 다음
	 * @param disclosureNextVO
	 * @return
	 * @throws Exception
	 */
	DisclosureNextVO selectPostNext(DisclosureNextVO disclosureNextVO) throws Exception;
	
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
