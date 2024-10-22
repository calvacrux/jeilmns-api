package jeilm.api.app.esg.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jeilm.api.app.esg.service.EsgMemberService;
import jeilm.api.app.esg.vo.EsgMemberVO;
import jeilm.api.cmm.json.JsonResult;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class EsgMemberController {

	private final EsgMemberService esgMemberService;
	
	@PostMapping("/v1/esg/member/list")
	public ResponseEntity<?> getEsgMemberList(@RequestBody EsgMemberVO esgMemberVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 아이디
		if (esgMemberVO.getLang().equals("ko")) {
			esgMemberVO.setMember_id("esg.member.ko");
		} else if (esgMemberVO.getLang().equals("en")) {
			esgMemberVO.setMember_id("esg.member.en");
		} else if (esgMemberVO.getLang().equals("cn")) {
			esgMemberVO.setMember_id("esg.member.cn");;
		} else {
			return JsonResult.fail("언어설정이 올바르지 않습니다.");
		}
		
		List<EsgMemberVO> resultList = esgMemberService.selectEsgMemberList(esgMemberVO);
		
		map.put("member_list", resultList);
				
		return JsonResult.success(map);		
	}
}
