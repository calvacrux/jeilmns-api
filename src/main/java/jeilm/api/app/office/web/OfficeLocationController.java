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
import jeilm.api.app.office.service.OfficeLocationService;
import jeilm.api.app.office.vo.OfficeLocationVO;
import jeilm.api.cmm.json.JsonResult;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OfficeLocationController {

	private final OfficeLocationService officeLocationService;
	
	/**
	 * 사업장 위치 리스트
	 * @param officeLocationVO
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/v1/office/location/list")
	public ResponseEntity<?> getOfficeLocationList(@RequestBody OfficeLocationVO officeLocationVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 아이디
		if (officeLocationVO.getLang().equals("ko")) {
			officeLocationVO.setLocation_id("location.ko");
		} else if (officeLocationVO.getLang().equals("en")) {
			officeLocationVO.setLocation_id("location.en");
		} else if (officeLocationVO.getLang().equals("cn")) {
			officeLocationVO.setLocation_id("location.cn");
		} else {
			return JsonResult.fail("언어설정이 올바르지 않습니다.");
		}
		
		List<OfficeLocationVO> resultList = officeLocationService.selectOfficeLocationList(officeLocationVO);
		
		map.put("location_list", resultList);
		
		return JsonResult.success(map);	
	}
	
}
