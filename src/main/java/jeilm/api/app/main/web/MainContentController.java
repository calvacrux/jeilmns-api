package jeilm.api.app.main.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jeilm.api.app.board.service.NewsBoardService;
import jeilm.api.app.board.vo.NewsBoardListVO;
import jeilm.api.app.invest.service.DisclosureService;
import jeilm.api.app.invest.vo.DisclosureListVO;
import jeilm.api.app.main.vo.MainContentVO;
import jeilm.api.cmm.json.JsonResult;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MainContentController {

	private final NewsBoardService newsBoardService;
	private final DisclosureService disclosureService;
	
	@PostMapping("/v1/main/content/list")
	public ResponseEntity<?> getMainContentList(@RequestBody MainContentVO mainContentVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 뉴스
		NewsBoardListVO newsBoardListVO = new NewsBoardListVO();
		newsBoardListVO.setLang(mainContentVO.getLang());
		
		// 뉴스 게시판 아이디
		if (newsBoardListVO.getLang().equals("ko")) {
			newsBoardListVO.setBoard_id("news.ko");
		} else if (newsBoardListVO.getLang().equals("en")) {
			newsBoardListVO.setBoard_id("news.en");
		} else if (newsBoardListVO.getLang().equals("cn")) {
			newsBoardListVO.setBoard_id("news.cn");
		} else {
			return JsonResult.fail("언어설정이 올바르지 않습니다.");
		}
		
		List<NewsBoardListVO> resultNewsList = newsBoardService.selectPostListForMain(newsBoardListVO);
		
		// 전자공고
		DisclosureListVO disclosureListVO = new DisclosureListVO();
		disclosureListVO.setLang(mainContentVO.getLang());
		
		// 전자공고 게시판 아이디
		if (disclosureListVO.getLang().equals("ko")) {
			disclosureListVO.setBoard_id("disclosure.ko");
		} else if (disclosureListVO.getLang().equals("en")) {
			disclosureListVO.setBoard_id("disclosure.en");
		} else if (disclosureListVO.getLang().equals("cn")) {
			disclosureListVO.setBoard_id("disclosure.cn");
		} else {
			return JsonResult.fail("언어설정이 올바르지 않습니다.");
		}
		
		List<DisclosureListVO> resultDisclosureList = disclosureService.selectPostListForMain(disclosureListVO);
		
		map.put("news_list", resultNewsList);
		map.put("disclosure_list", resultDisclosureList);				
		
		return JsonResult.success(map);	
	}
	
}
