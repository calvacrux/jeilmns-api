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
import jeilm.api.app.invest.service.BusinessService;
import jeilm.api.app.invest.vo.BusinessVO;
import jeilm.api.cmm.json.JsonResult;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BusinessController {

	private final BusinessService businessService;
	
	/**
	 * IR 게시판 리스트
	 * @param businessVO
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/v1/invest/business/list")
	public ResponseEntity<?> getInvestBusinessList(@RequestBody BusinessVO businessVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 게시판 아이디
		if (businessVO.getLang().equals("ko")) {
			businessVO.setBoard_id("business.ko");
		} else if (businessVO.getLang().equals("en")) {
			businessVO.setBoard_id("business.en");
		} else if (businessVO.getLang().equals("cn")) {
			businessVO.setBoard_id("business.cn");
		} else {
			return JsonResult.fail("언어설정이 올바르지 않습니다.");
		}
		
		businessVO.setPage_index(businessVO.getPage_index());
		businessVO.setPage_size(businessVO.getPage_size());
		
		// 페이징
		int intTotalCount = businessService.selectPostCount(businessVO);
		
		Map<String, Object> pagingVO = new HashMap<>();
		pagingVO.put("page_index", businessVO.getPage_index());
		pagingVO.put("total_count", intTotalCount);
		
		businessVO.setPage_offset((businessVO.getPage_index() -1) * businessVO.getPage_size());
		List<BusinessVO> resultList = businessService.selectPostList(businessVO);
		
		map.put("post_list", resultList);
		map.put("paging", pagingVO);
		
		return JsonResult.success(map);		
	}
}
