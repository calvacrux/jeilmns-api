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
import jeilm.api.app.esg.service.EsgAuditService;
import jeilm.api.app.esg.vo.EsgAuditVO;
import jeilm.api.cmm.json.JsonResult;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class EsgAuditController {

	private final EsgAuditService esgAuditService;
	
	@PostMapping("/v1/esg/audit/list")
	public ResponseEntity<?> getEsgAuditList(@RequestBody EsgAuditVO esgAuditVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 아이디
		if (esgAuditVO.getLang().equals("ko")) {
			esgAuditVO.setAudit_id("esg.audit.ko");
		} else if (esgAuditVO.getLang().equals("en")) {
			esgAuditVO.setAudit_id("esg.audit.en");
		} else if (esgAuditVO.getLang().equals("cn")) {
			esgAuditVO.setAudit_id("esg.audit.cn");;
		} else {
			return JsonResult.fail("언어설정이 올바르지 않습니다.");
		}
		
		List<EsgAuditVO> resultList = esgAuditService.selectEsgAuditList(esgAuditVO);
		
		map.put("audit_list", resultList);
				
		return JsonResult.success(map);		
	}
}
