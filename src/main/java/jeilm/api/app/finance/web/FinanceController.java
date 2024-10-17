package jeilm.api.app.finance.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jeilm.api.app.finance.service.FinanceService;
import jeilm.api.app.finance.vo.FinanceVO;
import jeilm.api.cmm.json.JsonResult;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FinanceController {
	
	private final FinanceService financeService;
	
	@PostMapping("/v1/invest/finance/list")
	public ResponseEntity<?> getPrivacyList(@RequestBody FinanceVO financeVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<FinanceVO> resultList = financeService.selectFinanceList(financeVO);
		
		map.put("finance_list", resultList);
				
		return JsonResult.success(map);		
	}

}
