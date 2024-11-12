package jeilm.api.app.office.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jeilm.api.app.office.service.OfficeNationService;
import jeilm.api.app.office.vo.OfficeNationVO;
import jeilm.api.cmm.json.JsonResult;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OfficeNationController {

	private final OfficeNationService officeNationService;
	
	/**
	 * 사업장 국가 리스트
	 * @param officeNationVO
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/v1/office/nation/list")
	public ResponseEntity<?> getOfficeNationList(@RequestBody OfficeNationVO officeNationVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 아이디
		if (officeNationVO.getLang().equals("ko")) {
			officeNationVO.setNation_id("nation.ko"); officeNationVO.setLocation_id("location.ko"); 
		} else if (officeNationVO.getLang().equals("en")) {
			officeNationVO.setNation_id("nation.en"); officeNationVO.setLocation_id("location.en"); 
		} else if (officeNationVO.getLang().equals("cn")) {
			officeNationVO.setNation_id("nation.cn"); officeNationVO.setLocation_id("location.cn"); 
		} else {
			return JsonResult.fail("언어설정이 올바르지 않습니다.");
		}
		
		List<OfficeNationVO> resultList = officeNationService.selectOfficeNationList(officeNationVO);
		
		map.put("nation_list", resultList);
		
		return JsonResult.success(map);	
	}
	
}
