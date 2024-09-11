package jeilm.api.app.popup.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jeilm.api.app.popup.service.PopupService;
import jeilm.api.app.popup.vo.PopupVO;
import jeilm.api.cmm.json.JsonResult;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PopupController {

	private final PopupService popupService;
	
	@PostMapping("/v1/popup/list")
	public ResponseEntity<?> getPopupList(@RequestBody PopupVO popupVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<PopupVO> resultList = popupService.selectPopupList(popupVO);
		
		map.put("popup_list", resultList);
		
		return JsonResult.success(map);		
	}
	
}
