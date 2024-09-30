package jeilm.api.app.popup.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jeilm.api.app.popup.service.PopupService;
import jeilm.api.app.popup.vo.PopupVO;
import jeilm.api.cmm.json.JsonResult;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PopupController {

	private final PopupService popupService;
	
	@PostMapping("/v1/popup/list")
	public ResponseEntity<?> getPopupList(@RequestBody PopupVO popupVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 팝업 아이디
		if (popupVO.getLang().equals("ko")) {
			popupVO.setPopup_id("popup.ko");
		} else if (popupVO.getLang().equals("en")) {
			popupVO.setPopup_id("popup.en");
		} else if (popupVO.getLang().equals("cn")) {
			popupVO.setPopup_id("popup.cn");
		} else {
			return JsonResult.fail("언어설정이 올바르지 않습니다.");
		}
		
		List<PopupVO> resultList = popupService.selectPopupList(popupVO);
		
		map.put("popup_list", resultList);
		
		return JsonResult.success(map);		
	}
	
}
