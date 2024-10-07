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
import jeilm.api.app.file.vo.DownFileVO;
import jeilm.api.app.invest.service.InformationService;
import jeilm.api.app.invest.vo.InformationVO;
import jeilm.api.cmm.json.JsonResult;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class InformationController {

	private final InformationService informationService;
	
	/**
	 * 공시정보관리규정 리스트
	 * @param informationVO
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/v1/invest/information/list")
	public ResponseEntity<?> getInvestInformationList(@RequestBody InformationVO informationVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 게시판 아이디
		if (informationVO.getLang().equals("ko")) {
			informationVO.setBoard_id("information.ko");
		} else if (informationVO.getLang().equals("en")) {
			informationVO.setBoard_id("information.en");
		} else if (informationVO.getLang().equals("cn")) {
			informationVO.setBoard_id("information.cn");
		} else {
			return JsonResult.fail("언어설정이 올바르지 않습니다.");
		}
		
		informationVO.setPage_index(informationVO.getPage_index());
		informationVO.setPage_size(informationVO.getPage_size());
		
		// 페이징
		int intTotalCount = informationService.selectPostCount(informationVO);
		
		Map<String, Object> pagingVO = new HashMap<>();
		pagingVO.put("page_index", informationVO.getPage_index());
		pagingVO.put("total_count", intTotalCount);
		
		informationVO.setPage_offset((informationVO.getPage_index() -1) * informationVO.getPage_size());
		List<InformationVO> resultList = informationService.selectPostList(informationVO);
		
		map.put("post_list", resultList);
		map.put("paging", pagingVO);
		
		return JsonResult.success(map);		
	}
	
	/**
	 * 공시정보관리규정 파일
	 * @param informationVO
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/v1/invest/information/downfile")
	public ResponseEntity<?> getInvestInformationDownFile(@RequestBody InformationVO informationVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 게시판 아이디
		if (informationVO.getLang().equals("ko")) {
			informationVO.setBoard_id("information.ko");
		} else if (informationVO.getLang().equals("en")) {
			informationVO.setBoard_id("information.en");
		} else if (informationVO.getLang().equals("cn")) {
			informationVO.setBoard_id("information.cn");
		} else {
			return JsonResult.fail("언어설정이 올바르지 않습니다.");
		}
		
		DownFileVO result = informationService.selectTopFile(informationVO);
		
		map.put("info", result);
		
		return JsonResult.success(map);	
	}
}
