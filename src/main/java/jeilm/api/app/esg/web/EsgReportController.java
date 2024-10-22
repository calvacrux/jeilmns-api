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
import jeilm.api.app.esg.service.EsgReportService;
import jeilm.api.app.esg.vo.EsgReportVO;
import jeilm.api.cmm.json.JsonResult;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class EsgReportController {

	private final EsgReportService esgReportService;
	
	@PostMapping("/v1/esg/report/list")
	public ResponseEntity<?> getEsgReportList(@RequestBody EsgReportVO esgReportVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 아이디
		if (esgReportVO.getLang().equals("ko")) {
			esgReportVO.setReport_id("esg.report.ko");
		} else if (esgReportVO.getLang().equals("en")) {
			esgReportVO.setReport_id("esg.report.en");
		} else if (esgReportVO.getLang().equals("cn")) {
			esgReportVO.setReport_id("esg.report.cn");;
		} else {
			return JsonResult.fail("언어설정이 올바르지 않습니다.");
		}
		
		List<EsgReportVO> resultList = esgReportService.selectEsgReportList(esgReportVO);
		
		map.put("report_list", resultList);
				
		return JsonResult.success(map);		
	}
}
