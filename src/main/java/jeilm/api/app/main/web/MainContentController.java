package jeilm.api.app.main.web;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jeilm.api.app.board.service.NewsBoardService;
import jeilm.api.app.board.vo.NewsBoardListVO;
import jeilm.api.app.invest.service.DartReportClient;
import jeilm.api.app.invest.service.DisclosureService;
import jeilm.api.app.invest.vo.DartReportsVO;
import jeilm.api.app.invest.vo.DartVO;
import jeilm.api.app.invest.vo.DisclosureMainVO;
import jeilm.api.app.main.vo.MainContentVO;
import jeilm.api.cmm.json.JsonResult;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MainContentController {

	@Value("${global.dart.url.report}")
	private String url_report;
	
	@Value("${global.dart.api.key}")
	private String crtfc_key;
	
	@Value("${global.dart.corp.code}")
	private String corp_code;
	
	@Value("${global.dart.corp.cls}")
	private String corp_cls;
	
	@Value("${global.dart.start}")
	private String bgn_de;
	
	private final NewsBoardService newsBoardService;
	private final DisclosureService disclosureService;
	private final DartReportClient dartReportClient;
	
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
		DisclosureMainVO disclosureMainVO = new DisclosureMainVO();
		disclosureMainVO.setLang(mainContentVO.getLang());
		
		// 전자공고 게시판 아이디
		if (disclosureMainVO.getLang().equals("ko")) {
			disclosureMainVO.setBoard_id("disclosure.ko");
		} else if (disclosureMainVO.getLang().equals("en")) {
			disclosureMainVO.setBoard_id("disclosure.en");
		} else if (disclosureMainVO.getLang().equals("cn")) {
			disclosureMainVO.setBoard_id("disclosure.cn");
		} else {
			return JsonResult.fail("언어설정이 올바르지 않습니다.");
		}
		
		List<DisclosureMainVO> resultDisclosureList = disclosureService.selectPostListForMain(disclosureMainVO);
		
		
		// 공시정보 - 국문의 경우만 DART 공시와 합쳐서 4개 리턴
		if (mainContentVO.getLang().equals("ko")) {
			String end_de = LocalDate.now().format( DateTimeFormatter.BASIC_ISO_DATE);
			
			URI dartReportUri = URI.create(url_report 
					+ "?crtfc_key=" + crtfc_key 
					+ "&corp_code=" + corp_code 
					+ "&corp_cls=" + corp_cls 
					+ "&bgn_de=" + bgn_de 
					+ "&end_de=" + end_de
					+ "&page_no=1"
					+ "&page_count=4");
			
			DartVO resultVO = new Gson().fromJson(dartReportClient.getDartReportClientString(dartReportUri), DartVO.class);
			
			if (resultVO.getStatus().equals("000")) {
				List<DartReportsVO> resultReportList = resultVO.getList();
				
				for (DartReportsVO vo : resultReportList) {
					DisclosureMainVO dartVO = new DisclosureMainVO();
					dartVO.setPost_type("dart");
					dartVO.setPost_sn(vo.getRcept_no());
					dartVO.setPost_title(vo.getReport_nm());
					dartVO.setReg_dt(vo.getRcept_dt().substring(0, 4) + "-" + vo.getRcept_dt().substring(4, 6)+ "-" + vo.getRcept_dt().substring(6, 8) + " 00:00:00");
					resultDisclosureList.add(dartVO);
				}
				
				Collections.sort(resultDisclosureList);
				
				int resultSize = resultDisclosureList.size(); 
				
				for (int i = 0; i < resultSize; i++) {
					if (i > 3) {
						resultDisclosureList.remove(i);
						resultSize--;
						i--;
					}
				}
			}
		}
		
		map.put("news_list", resultNewsList);
		map.put("invest_list", resultDisclosureList);	
		
		return JsonResult.success(map);	
	}
	
}
