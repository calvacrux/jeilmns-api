package jeilm.api.app.invest.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jeilm.api.app.invest.service.AuditService;
import jeilm.api.app.invest.vo.AuditVO;
import jeilm.api.cmm.json.JsonResult;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuditController {

	private final AuditService auditService;
	
	/**
	 * IR 게시판 리스트
	 * @param auditVO
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/v1/invest/audit/list")
	public ResponseEntity<?> getInvestAuditList(@RequestBody AuditVO auditVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 게시판 아이디
		if (auditVO.getLang().equals("ko")) {
			auditVO.setBoard_id("audit.ko");
		} else if (auditVO.getLang().equals("en")) {
			auditVO.setBoard_id("audit.en");
		} else if (auditVO.getLang().equals("cn")) {
			auditVO.setBoard_id("audit.cn");
		} else {
			return JsonResult.fail("언어설정이 올바르지 않습니다.");
		}
		
		auditVO.setPage_index(auditVO.getPage_index());
		auditVO.setPage_size(auditVO.getPage_size());
		
		// 페이징
		int intTotalCount = auditService.selectPostCount(auditVO);
		
		Map<String, Object> pagingVO = new HashMap<>();
		pagingVO.put("page_index", auditVO.getPage_index());
		pagingVO.put("total_count", intTotalCount);
		
		auditVO.setPage_offset((auditVO.getPage_index() -1) * auditVO.getPage_size());
		List<AuditVO> resultList = auditService.selectPostList(auditVO);
		
		map.put("post_list", resultList);
		map.put("paging", pagingVO);
		
		return JsonResult.success(map);		
	}
}
