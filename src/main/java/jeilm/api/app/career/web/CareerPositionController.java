package jeilm.api.app.career.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jeilm.api.app.career.service.CareerPositionService;
import jeilm.api.app.career.vo.CareerPositionVO;
import jeilm.api.cmm.json.JsonResult;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CareerPositionController {

	private final CareerPositionService careerPositionService;
	
	@PostMapping("/v1/career/position/list")
	public ResponseEntity<?> getCareerPositionList(@RequestBody CareerPositionVO careerPositionVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 채용공고 아이디
		if (careerPositionVO.getLang().equals("ko")) {
			careerPositionVO.setPosition_id("position.ko");
		} else if (careerPositionVO.getLang().equals("en")) {
			careerPositionVO.setPosition_id("position.en");
		} else if (careerPositionVO.getLang().equals("cn")) {
			careerPositionVO.setPosition_id("position.cn");
		} else {
			return JsonResult.fail("언어설정이 올바르지 않습니다.");
		}
		
		List<CareerPositionVO> resultList = careerPositionService.selectPositionList(careerPositionVO);
		
		map.put("position_list", resultList);
		
		return JsonResult.success(map);	
		
	}
}
