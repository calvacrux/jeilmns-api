package jeilm.api.app.privacy.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jeilm.api.app.privacy.service.PrivacyService;
import jeilm.api.app.privacy.vo.PrivacyVO;
import jeilm.api.cmm.json.JsonResult;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PrivacyController {

	private final PrivacyService privacyService;
	
	@PostMapping("/v1/privacy/viewlist")
	public ResponseEntity<?> getPrivacyList(@RequestBody PrivacyVO privacyVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 개인정보 아이디
		if (privacyVO.getLang().equals("ko")) {
			privacyVO.setPrivacy_id("privacy.ko");
		} else if (privacyVO.getLang().equals("en")) {
			privacyVO.setPrivacy_id("privacy.en");
		} else if (privacyVO.getLang().equals("cn")) {
			privacyVO.setPrivacy_id("privacy.cn");
		} else {
			return JsonResult.fail("언어설정이 올바르지 않습니다.");
		}
		
		PrivacyVO result = new PrivacyVO();
		
		// 최신
		if (privacyVO.getPrivacy_version().equals("last")) {
			result = privacyService.selectPrivacyTop(privacyVO);
		} else {
			result = privacyService.selectPrivacy(privacyVO);
		}
		
		List<PrivacyVO> resultList = privacyService.selectPrivacyList(privacyVO);
		
		map.put("privacy", result);
		map.put("privacy_list", resultList);
				
		return JsonResult.success(map);		
	}
	
}
