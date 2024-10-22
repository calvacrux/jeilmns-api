package jeilm.api.app.esg.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import jeilm.api.app.esg.service.EsgMemberService;
import jeilm.api.app.esg.service.mapper.EsgMemberMapper;
import jeilm.api.app.esg.vo.EsgMemberVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EsgMemberServiceImpl implements EsgMemberService {
	
	private final EsgMemberMapper esgMemberMapper;

	@Override
	public List<EsgMemberVO> selectEsgMemberList(EsgMemberVO esgMemberVO) throws Exception {
		return esgMemberMapper.selectEsgMemberList(esgMemberVO);
	}

}
