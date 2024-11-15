package jeilm.api.app.history.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jeilm.api.app.history.service.HistoryGroupService;
import jeilm.api.app.history.vo.HistoryGroupVO;
import jeilm.api.cmm.json.JsonResult;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class HistoryGroupController {

	private final HistoryGroupService historyGroupService;
	
	/**
	 * 연혁 리스트
	 * @param historyVO
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/v1/chronicle/list")
	public ResponseEntity<?> getHistoryList(@RequestBody HistoryGroupVO historyGroupVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		historyGroupVO.setLang(historyGroupVO.getLang());
		List<HistoryGroupVO> resultList = new ArrayList<HistoryGroupVO>();
		
		if (historyGroupVO.getLang().equals("ko")) {
			resultList = historyGroupService.selectHistoryGroupListKo(historyGroupVO);
		} else if (historyGroupVO.getLang().equals("en")) {
			resultList = historyGroupService.selectHistoryGroupListEn(historyGroupVO);
		} else if (historyGroupVO.getLang().equals("cn")) {
			resultList = historyGroupService.selectHistoryGroupListCn(historyGroupVO);
		} else {
			return JsonResult.fail("언어설정이 올바르지 않습니다.");
		}
		
		map.put("chronicle_list", resultList);
		
		return JsonResult.success(map);		
	}
	
}
