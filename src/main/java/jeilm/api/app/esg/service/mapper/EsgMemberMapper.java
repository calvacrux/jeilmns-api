package jeilm.api.app.esg.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jeilm.api.app.esg.vo.EsgMemberVO;

@Mapper
public interface EsgMemberMapper {

	/**
	 * 위원회 리스트 조회
	 * @param esgMemberVO
	 * @return
	 * @throws Exception
	 */
	List<EsgMemberVO> selectEsgMemberList(EsgMemberVO esgMemberVO)  throws Exception;
	
}
