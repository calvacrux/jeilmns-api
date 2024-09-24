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
import jeilm.api.app.invest.service.DisclosureService;
import jeilm.api.app.invest.vo.DisclosureVO;
import jeilm.api.cmm.json.JsonResult;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DisclosureController {

	private final DisclosureService disclosureService;
	
	/**
	 * IR 게시판 리스트
	 * @param disclosureVO
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/v1/invest/disclosure/list")
	public ResponseEntity<?> getInvestDisclosureList(@RequestBody DisclosureVO disclosureVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 게시판 아이디
		if (disclosureVO.getLang().equals("ko")) {
			disclosureVO.setBoard_id("disclosure.ko");
		} else if (disclosureVO.getLang().equals("en")) {
			disclosureVO.setBoard_id("disclosure.en");
		} else if (disclosureVO.getLang().equals("cn")) {
			disclosureVO.setBoard_id("disclosure.cn");
		} else {
			return JsonResult.fail("언어설정이 올바르지 않습니다.");
		}
		
		disclosureVO.setPage_index(disclosureVO.getPage_index());
		disclosureVO.setPage_size(disclosureVO.getPage_size());
		
		// 페이징
		int intTotalCount = disclosureService.selectPostCount(disclosureVO);
		
		Map<String, Object> pagingVO = new HashMap<>();
		pagingVO.put("page_index", disclosureVO.getPage_index());
		pagingVO.put("total_count", intTotalCount);
		
		disclosureVO.setPage_offset((disclosureVO.getPage_index() -1) * disclosureVO.getPage_size());
		List<DisclosureVO> resultList = disclosureService.selectPostList(disclosureVO);
		
		map.put("post_list", resultList);
		map.put("paging", pagingVO);
		
		return JsonResult.success(map);		
	}
}
