package jeilm.api.app.board.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jeilm.api.app.board.service.InvestBoardService;
import jeilm.api.app.board.vo.InvestBoardDetailVO;
import jeilm.api.app.board.vo.InvestBoardListVO;
import jeilm.api.cmm.json.JsonResult;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class InvestBoardController {
	
	private final InvestBoardService investBoardService;
	
	/**
	 * 뉴스 게시판 리스트
	 * @param investBoardListVO
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/v1/investboard/list")
	public ResponseEntity<?> getInvestBoardList(@RequestBody InvestBoardListVO investBoardListVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 게시판 아이디
		if (investBoardListVO.getLang().equals("ko")) {
			investBoardListVO.setBoard_id("invest.ko");
		} else if (investBoardListVO.getLang().equals("en")) {
			investBoardListVO.setBoard_id("invest.en");
		} else if (investBoardListVO.getLang().equals("cn")) {
			investBoardListVO.setBoard_id("invest.cn");
		} else {
			return JsonResult.fail("언어설정이 올바르지 않습니다.");
		}
		
		investBoardListVO.setPage_index(investBoardListVO.getPage_index());
		investBoardListVO.setPage_size(investBoardListVO.getPage_size());
		
		// 페이징
		int intTotalCount = investBoardService.selectPostCount(investBoardListVO);
		
		Map<String, Object> pagingVO = new HashMap<>();
		pagingVO.put("page_index", investBoardListVO.getPage_index());
		pagingVO.put("total_count", intTotalCount);
		
		investBoardListVO.setPage_offset((investBoardListVO.getPage_index() -1) * investBoardListVO.getPage_size());
		List<InvestBoardListVO> resultList = investBoardService.selectPostList(investBoardListVO);
		
		map.put("post_list", resultList);
		map.put("paging", pagingVO);
		
		return JsonResult.success(map);		
	}
	
	@PostMapping("/v1/investboard/view")
	public ResponseEntity<?> getInvestBoardPost(@RequestBody InvestBoardDetailVO investBoardDetailVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		InvestBoardDetailVO result = investBoardService.selectPost(investBoardDetailVO);
		// result.setPost_title(StringUtil.str2html(result.getPost_title()));
		// result.setPost_content(StringUtil.str2html(result.getPost_content()));
		
		//조회수 증가 - 쿠키를 이용한 조회수 증가 방지
		Cookie[] cookies = request.getCookies();
		Cookie newCookie = null;
		Cookie viewCookie = null;
		
		if (cookies == null || cookies.length == 0) {
			newCookie = new Cookie("viewPost", "|" + investBoardDetailVO.getPost_sn() + "|");
			newCookie.setPath("/");
			response.addCookie(newCookie);
			investBoardService.updateViewCount(investBoardDetailVO);
		} else {
			for (int i = 0; i < cookies.length; i++) {
				 if (cookies[i].getName().equals("viewPost")) {
					 viewCookie = cookies[i];
				 }
			}
			
			if (viewCookie == null) {
				newCookie = new Cookie("viewPost", "|" + investBoardDetailVO.getPost_sn() + "|");
				newCookie.setPath("/");
				response.addCookie(newCookie);
				investBoardService.updateViewCount(investBoardDetailVO);
			} else {
				String value = viewCookie.getValue();
				if (value.indexOf("|" + investBoardDetailVO.getPost_sn() + "|") < 0) {
					value = value + "|" + investBoardDetailVO.getPost_sn() + "|";
					viewCookie.setValue(value);
					viewCookie.setPath("/");
					response.addCookie(viewCookie);
					investBoardService.updateViewCount(investBoardDetailVO);
				}
			}
		}
		
		if (result == null) {
			return JsonResult.fail("게시물 번호가 올바르지 않습니다.");
		}
		
		map.put("post", result);
		
		return JsonResult.success(map);	
	}

}
