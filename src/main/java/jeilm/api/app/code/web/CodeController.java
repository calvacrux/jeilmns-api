package jeilm.api.app.code.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jeilm.api.app.code.service.CodeService;
import jeilm.api.app.code.vo.CodeVO;
import jeilm.api.cmm.json.JsonResult;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CodeController {
	
	private final CodeService codeService;
	
	/**
	 * 코드 리스트
	 * @param codeVO
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/v1/code/list")
	public ResponseEntity<?> getCodeList(@RequestBody CodeVO codeVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		codeVO.setParent_code_id(codeVO.getParent_code_id());
		
		List<CodeVO> resultList = codeService.selectCodeList(codeVO);
		
		map.put("code_list", resultList);
		
		return JsonResult.success(map);	
	}

}
