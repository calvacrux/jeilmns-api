package jeilm.api.app.support.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jeilm.api.app.support.service.SupportContactService;
import jeilm.api.app.support.vo.SupportContactVO;
import jeilm.api.cmm.json.JsonResult;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SupportContactController {

	private final SupportContactService supportContactService;
	
	@PostMapping("/v1/support/contact/list")
	public ResponseEntity<?> getSupportContactList(@RequestBody SupportContactVO supportContactVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 개인정보 아이디
		if (supportContactVO.getLang().equals("ko")) {
			supportContactVO.setContact_id("contact.ko");
		} else if (supportContactVO.getLang().equals("en")) {
			supportContactVO.setContact_id("contact.en");
		} else if (supportContactVO.getLang().equals("cn")) {
			supportContactVO.setContact_id("contact.cn");
		} else {
			return JsonResult.fail("언어설정이 올바르지 않습니다.");
		}
		
		List<SupportContactVO> resultList = supportContactService.selectSupportContactList(supportContactVO);
		
		map.put("contact_list", resultList);
				
		return JsonResult.success(map);		
	}
	
}
