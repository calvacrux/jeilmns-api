package jeilm.api.app.esg.service;

import java.util.List;

import jeilm.api.app.esg.vo.EsgMemberVO;

public interface EsgMemberService {

	/**
	 * 위원회 리스트 조회
	 * @param esgMemberVO
	 * @return
	 * @throws Exception
	 */
	List<EsgMemberVO> selectEsgMemberList(EsgMemberVO esgMemberVO)  throws Exception;
	
}
