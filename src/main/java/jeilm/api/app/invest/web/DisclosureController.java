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
import jeilm.api.app.invest.vo.DisclosureDetailVO;
import jeilm.api.app.invest.vo.DisclosureListVO;
import jeilm.api.app.invest.vo.DisclosureNextVO;
import jeilm.api.app.invest.vo.DisclosurePrevVO;
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
	
	/**
	 * IR 게시판 상세
	 * @param disclosureDetailVO
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/v1/invest/disclosure/view")
	public ResponseEntity<?> getInvestDisclosurePost(@RequestBody DisclosureDetailVO disclosureDetailVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		DisclosureDetailVO result = disclosureService.selectPost(disclosureDetailVO);
		
		// 이전글/다음글
		DisclosurePrevVO result_prev = new DisclosurePrevVO();
		DisclosureNextVO result_next = new DisclosureNextVO();
		
		if (disclosureDetailVO.getLang().equals("ko")) {
			result_prev.setBoard_id("disclosure.ko");
			result_next.setBoard_id("disclosure.ko");
		} else if (disclosureDetailVO.getLang().equals("en")) {
			result_prev.setBoard_id("disclosure.en");
			result_next.setBoard_id("disclosure.en");
		} else if (disclosureDetailVO.getLang().equals("cn")) {
			result_prev.setBoard_id("disclosure.cn");
			result_next.setBoard_id("disclosure.cn");
		} else {
			return JsonResult.fail("언어설정이 올바르지 않습니다.");
		}
		
		result_prev.setPost_sn(disclosureDetailVO.getPost_sn());
		
		result_next.setPost_sn(disclosureDetailVO.getPost_sn());
		
		result_prev = disclosureService.selectPostPrev(result_prev);
		
		result_next = disclosureService.selectPostNext(result_next);
		
		if (result == null) {
			return JsonResult.fail("게시물 번호가 올바르지 않습니다.");
		}
		
		map.put("post", result);
		map.put("post_prev", result_prev);
		map.put("post_next", result_next);
				
		return JsonResult.success(map);	
	}
}
