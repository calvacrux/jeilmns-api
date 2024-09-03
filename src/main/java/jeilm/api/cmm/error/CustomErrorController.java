package jeilm.api.cmm.error;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jeilm.api.cmm.json.JsonResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class CustomErrorController implements ErrorController {
	
	@RequestMapping(value="/error", method={RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> Error(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return JsonResult.fail("오류 입니다.");
	}
}
