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
import jeilm.api.app.history.service.HistoryService;
import jeilm.api.app.history.vo.HistoryVO;
import jeilm.api.cmm.json.JsonResult;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class HistoryController {

	private final HistoryService historyService;
	
	/**
	 * 연혁 리스트
	 * @param historyVO
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/v1/history/list")
	public ResponseEntity<?> getHistoryList(@RequestBody HistoryVO historyVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		historyVO.setLang(historyVO.getLang());
		List<HistoryVO> resultList = new ArrayList<HistoryVO>();
		
		if (historyVO.getLang().equals("ko")) {
			resultList = historyService.selectHistoryListKo(historyVO);
		} else if (historyVO.getLang().equals("en")) {
			resultList = historyService.selectHistoryListEn(historyVO);
		} else if (historyVO.getLang().equals("cn")) {
			resultList = historyService.selectHistoryListCn(historyVO);
		} else {
			return JsonResult.fail("언어설정이 올바르지 않습니다.");
		}
		
		map.put("history_list", resultList);
		
		return JsonResult.success(map);		
	}
	
}
