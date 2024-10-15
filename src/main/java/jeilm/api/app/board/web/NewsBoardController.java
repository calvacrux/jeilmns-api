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
import jeilm.api.app.board.service.NewsBoardService;
import jeilm.api.app.board.vo.NewsBoardDetailVO;
import jeilm.api.app.board.vo.NewsBoardListVO;
import jeilm.api.app.board.vo.NewsBoardNextVO;
import jeilm.api.app.board.vo.NewsBoardPrevVO;
import jeilm.api.cmm.json.JsonResult;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class NewsBoardController {
	
	private final NewsBoardService newsBoardService;
	
	/**
	 * 뉴스 게시판 리스트
	 * @param newsBoardListVO
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/v1/newsboard/list")
	public ResponseEntity<?> getNewsBoardList(@RequestBody NewsBoardListVO newsBoardListVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 게시판 아이디
		if (newsBoardListVO.getLang().equals("ko")) {
			newsBoardListVO.setBoard_id("news.ko");
		} else if (newsBoardListVO.getLang().equals("en")) {
			newsBoardListVO.setBoard_id("news.en");
		} else if (newsBoardListVO.getLang().equals("cn")) {
			newsBoardListVO.setBoard_id("news.cn");
		} else {
			return JsonResult.fail("언어설정이 올바르지 않습니다.");
		}
		
		newsBoardListVO.setPage_index(newsBoardListVO.getPage_index());
		newsBoardListVO.setPage_size(newsBoardListVO.getPage_size());
		
		// 페이징
		int intTotalCount = newsBoardService.selectPostCount(newsBoardListVO);
		
		Map<String, Object> pagingVO = new HashMap<>();
		pagingVO.put("page_index", newsBoardListVO.getPage_index());
		pagingVO.put("total_count", intTotalCount);
		
		newsBoardListVO.setPage_offset((newsBoardListVO.getPage_index() -1) * newsBoardListVO.getPage_size());
		List<NewsBoardListVO> resultList = newsBoardService.selectPostList(newsBoardListVO);
		
		map.put("post_list", resultList);
		map.put("paging", pagingVO);
		
		return JsonResult.success(map);		
	}
	
	/**
	 * 뉴스 게시판 상세
	 * @param newsBoardDetailVO
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/v1/newsboard/view")
	public ResponseEntity<?> getNewsBoardPost(@RequestBody NewsBoardDetailVO newsBoardDetailVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		NewsBoardDetailVO result = newsBoardService.selectPost(newsBoardDetailVO);
		// result.setPost_title(StringUtil.str2html(result.getPost_title()));
		// result.setPost_content(StringUtil.str2html(result.getPost_content()));
		
		// 이전글/다음글
		NewsBoardPrevVO result_prev = new NewsBoardPrevVO();
		NewsBoardNextVO result_next = new NewsBoardNextVO();
		
		if (newsBoardDetailVO.getLang().equals("ko")) {
			result_prev.setBoard_id("news.ko");
			result_next.setBoard_id("news.ko");
		} else if (newsBoardDetailVO.getLang().equals("en")) {
			result_prev.setBoard_id("news.en");
			result_next.setBoard_id("news.en");
		} else if (newsBoardDetailVO.getLang().equals("cn")) {
			result_prev.setBoard_id("news.cn");
			result_next.setBoard_id("news.cn");
		} else {
			return JsonResult.fail("언어설정이 올바르지 않습니다.");
		}
		
		
		result_prev.setPost_sn(newsBoardDetailVO.getPost_sn());
		result_prev.setPost_cat_list(newsBoardDetailVO.getPost_cat_list());
		result_prev.setSearch_text(newsBoardDetailVO.getSearch_text());
		
		result_next.setPost_sn(newsBoardDetailVO.getPost_sn());
		result_next.setPost_cat_list(newsBoardDetailVO.getPost_cat_list());
		result_next.setSearch_text(newsBoardDetailVO.getSearch_text());
		
		result_prev = newsBoardService.selectPostPrev(result_prev);
		result_next = newsBoardService.selectPostNext(result_next);
				
		//조회수 증가 - 쿠키를 이용한 조회수 증가 방지
		Cookie[] cookies = request.getCookies();
		Cookie newCookie = null;
		Cookie viewCookie = null;
		
		if (cookies == null || cookies.length == 0) {
			newCookie = new Cookie("viewPost", "|" + newsBoardDetailVO.getPost_sn() + "|");
			newCookie.setPath("/");
			response.addCookie(newCookie);
			newsBoardService.updateViewCount(newsBoardDetailVO);
		} else {
			for (int i = 0; i < cookies.length; i++) {
				 if (cookies[i].getName().equals("viewPost")) {
					 viewCookie = cookies[i];
				 }
			}
			
			if (viewCookie == null) {
				newCookie = new Cookie("viewPost", "|" + newsBoardDetailVO.getPost_sn() + "|");
				newCookie.setPath("/");
				response.addCookie(newCookie);
				newsBoardService.updateViewCount(newsBoardDetailVO);
			} else {
				String value = viewCookie.getValue();
				if (value.indexOf("|" + newsBoardDetailVO.getPost_sn() + "|") < 0) {
					value = value + "|" + newsBoardDetailVO.getPost_sn() + "|";
					viewCookie.setValue(value);
					viewCookie.setPath("/");
					response.addCookie(viewCookie);
					newsBoardService.updateViewCount(newsBoardDetailVO);
				}
			}
		}
		
		if (result == null) {
			return JsonResult.fail("게시물 번호가 올바르지 않습니다.");
		}
		
		map.put("post", result);
		map.put("post_prev", result_prev);
		map.put("post_next", result_next);
				
		return JsonResult.success(map);	
	}

}
