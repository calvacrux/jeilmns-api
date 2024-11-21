package jeilm.api.app.esg.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jeilm.api.app.esg.service.EsgCodeService;
import jeilm.api.app.esg.vo.EsgCodeVO;
import jeilm.api.app.file.vo.DownFileVO;
import jeilm.api.cmm.json.JsonResult;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class EsgCodeController {

	private final EsgCodeService esgCodeService;
	
	/**
	 * ESG 인증서 리스트
	 * @param esgCodeVO
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/v1/esg/code/list")
	public ResponseEntity<?> getInvestEsgCodeList(@RequestBody EsgCodeVO esgCodeVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 게시판 아이디
		if (esgCodeVO.getLang().equals("ko")) {
			esgCodeVO.setBoard_id("esgcode.ko");
		} else if (esgCodeVO.getLang().equals("en")) {
			esgCodeVO.setBoard_id("esgcode.en");
		} else if (esgCodeVO.getLang().equals("cn")) {
			esgCodeVO.setBoard_id("esgcode.cn");
		} else {
			return JsonResult.fail("언어설정이 올바르지 않습니다.");
		}
		
		esgCodeVO.setPage_index(esgCodeVO.getPage_index());
		esgCodeVO.setPage_size(esgCodeVO.getPage_size());
		
		// 페이징
		int intTotalCount = esgCodeService.selectPostCount(esgCodeVO);
		
		Map<String, Object> pagingVO = new HashMap<>();
		pagingVO.put("page_index", esgCodeVO.getPage_index());
		pagingVO.put("total_count", intTotalCount);
		
		esgCodeVO.setPage_offset((esgCodeVO.getPage_index() -1) * esgCodeVO.getPage_size());
		List<EsgCodeVO> resultList = esgCodeService.selectPostList(esgCodeVO);
		
		map.put("post_list", resultList);
		map.put("paging", pagingVO);
		
		return JsonResult.success(map);		
	}
	
	/**
	 * ESG 인증서 파일
	 * @param esgCodeVO
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/v1/esg/iso/downfile")
	public ResponseEntity<?> getInvestEsgCodeDownFile(@RequestBody EsgCodeVO esgCodeVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 게시판 아이디
		if (esgCodeVO.getLang().equals("ko")) {
			esgCodeVO.setBoard_id("esgcode.ko");
		} else if (esgCodeVO.getLang().equals("en")) {
			esgCodeVO.setBoard_id("esgcode.en");
		} else if (esgCodeVO.getLang().equals("cn")) {
			esgCodeVO.setBoard_id("esgcode.cn");
		} else {
			return JsonResult.fail("언어설정이 올바르지 않습니다.");
		}
		
		EsgCodeVO result = esgCodeService.selectTopFile(esgCodeVO);
		
		DownFileVO iso9001VO = new DownFileVO();
		iso9001VO.setFile_name(null);
		iso9001VO.setFile_url(null);
		
		if (result != null && result.getFile_user_nm_iso9001() != null && result.getFile_path_iso9001() != null) {
			iso9001VO.setFile_name(result.getFile_user_nm_iso9001());
			iso9001VO.setFile_path(result.getFile_path_iso9001());
			iso9001VO.setFile_url(result.getFile_url_iso9001());
		}
		
		DownFileVO iso14001VO = new DownFileVO();
		iso14001VO.setFile_name(null);
		iso14001VO.setFile_url(null);
		
		if (result != null && result.getFile_user_nm_iso14001() != null && result.getFile_path_iso14001() != null) {
			iso14001VO.setFile_name(result.getFile_user_nm_iso14001());
			iso14001VO.setFile_path(result.getFile_path_iso14001());
			iso14001VO.setFile_url(result.getFile_url_iso14001());
		}
		
		DownFileVO iso45001VO = new DownFileVO();
		iso45001VO.setFile_name(null);
		iso45001VO.setFile_url(null);
		
		if (result != null && result.getFile_user_nm_iso45001() != null && result.getFile_path_iso45001() != null) {
			iso45001VO.setFile_name(result.getFile_user_nm_iso45001());
			iso45001VO.setFile_path(result.getFile_path_iso45001());
			iso45001VO.setFile_url(result.getFile_url_iso45001());
		}
		
		map.put("iso9001", iso9001VO);
		map.put("iso14001", iso14001VO);
		map.put("iso45001", iso45001VO);
		
		return JsonResult.success(map);	
	}
}
