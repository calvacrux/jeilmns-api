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
import jeilm.api.app.invest.vo.DisclosureListVO;
import jeilm.api.cmm.json.JsonResult;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DisclosureController {

	private final DisclosureService disclosureService;
	
	/**
	 * IR 게시판 리스트
	 * @param disclosureListVO
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/v1/invest/disclosure/list")
	public ResponseEntity<?> getInvestDisclosureList(@RequestBody DisclosureListVO disclosureListVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 게시판 아이디
		if (disclosureListVO.getLang().equals("ko")) {
			disclosureListVO.setBoard_id("disclosure.ko");
		} else if (disclosureListVO.getLang().equals("en")) {
			disclosureListVO.setBoard_id("disclosure.en");
		} else if (disclosureListVO.getLang().equals("cn")) {
			disclosureListVO.setBoard_id("disclosure.cn");
		} else {
			return JsonResult.fail("언어설정이 올바르지 않습니다.");
		}
		
		disclosureListVO.setPage_index(disclosureListVO.getPage_index());
		disclosureListVO.setPage_size(disclosureListVO.getPage_size());
		
		// 페이징
		int intTotalCount = disclosureService.selectPostCount(disclosureListVO);
		
		Map<String, Object> pagingVO = new HashMap<>();
		pagingVO.put("page_index", disclosureListVO.getPage_index());
		pagingVO.put("total_count", intTotalCount);
		
		disclosureListVO.setPage_offset((disclosureListVO.getPage_index() -1) * disclosureListVO.getPage_size());
		List<DisclosureListVO> resultList = disclosureService.selectPostList(disclosureListVO);
		
		map.put("post_list", resultList);
		map.put("paging", pagingVO);
		
		return JsonResult.success(map);		
	}
}
