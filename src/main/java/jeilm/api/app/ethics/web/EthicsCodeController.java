package jeilm.api.app.ethics.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jeilm.api.app.ethics.service.EthicsCodeService;
import jeilm.api.app.ethics.vo.EthicsCodeVO;
import jeilm.api.app.file.vo.DownFileVO;
import jeilm.api.cmm.json.JsonResult;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class EthicsCodeController {

	private final EthicsCodeService ethicsCodeService;
	
	/**
	 * 윤리강령 정책서 리스트
	 * @param ethicsCodeVO
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/v1/ethics/code/list")
	public ResponseEntity<?> getInvestEthicsCodeList(@RequestBody EthicsCodeVO ethicsCodeVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 게시판 아이디
		if (ethicsCodeVO.getLang().equals("ko")) {
			ethicsCodeVO.setBoard_id("ethicscode.ko");
		} else if (ethicsCodeVO.getLang().equals("en")) {
			ethicsCodeVO.setBoard_id("ethicscode.en");
		} else if (ethicsCodeVO.getLang().equals("cn")) {
			ethicsCodeVO.setBoard_id("ethicscode.cn");
		} else {
			return JsonResult.fail("언어설정이 올바르지 않습니다.");
		}
		
		ethicsCodeVO.setPage_index(ethicsCodeVO.getPage_index());
		ethicsCodeVO.setPage_size(ethicsCodeVO.getPage_size());
		
		// 페이징
		int intTotalCount = ethicsCodeService.selectPostCount(ethicsCodeVO);
		
		Map<String, Object> pagingVO = new HashMap<>();
		pagingVO.put("page_index", ethicsCodeVO.getPage_index());
		pagingVO.put("total_count", intTotalCount);
		
		ethicsCodeVO.setPage_offset((ethicsCodeVO.getPage_index() -1) * ethicsCodeVO.getPage_size());
		List<EthicsCodeVO> resultList = ethicsCodeService.selectPostList(ethicsCodeVO);
		
		map.put("post_list", resultList);
		map.put("paging", pagingVO);
		
		return JsonResult.success(map);		
	}
	
	/**
	 * 윤리강령 정책서 파일
	 * @param ethicsCodeVO
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/v1/ethics/code/downfile")
	public ResponseEntity<?> getInvestEthicsCodeDownFile(@RequestBody EthicsCodeVO ethicsCodeVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 게시판 아이디
		if (ethicsCodeVO.getLang().equals("ko")) {
			ethicsCodeVO.setBoard_id("ethicscode.ko");
		} else if (ethicsCodeVO.getLang().equals("en")) {
			ethicsCodeVO.setBoard_id("ethicscode.en");
		} else if (ethicsCodeVO.getLang().equals("cn")) {
			ethicsCodeVO.setBoard_id("ethicscode.cn");
		} else {
			return JsonResult.fail("언어설정이 올바르지 않습니다.");
		}
		
		DownFileVO result = ethicsCodeService.selectTopFile(ethicsCodeVO);
		
		map.put("info", result);
		
		return JsonResult.success(map);	
	}
}
