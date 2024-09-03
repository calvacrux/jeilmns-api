package jeilm.api.cmm.json;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class JsonResult {
	
	private static final boolean RESULT_TRUE = true;
	private static final boolean RESULT_FALSE = false;
	
	/**
	 * 성공응답
	 * @param httpStatus
	 * @param message
	 * @param object
	 * @return
	 */
	public static ResponseEntity<?> success(HttpStatus httpStatus, String message, Object object) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", JsonResult.RESULT_TRUE);
		map.put("message", message);
		map.put("data", object);
		
		return ResponseEntity.status(httpStatus).body(map);
	}
	
	public static ResponseEntity<?> success(HttpStatus httpStatus, Object object) {
		return JsonResult.success(httpStatus, null, object);
	}
	
	public static ResponseEntity<?> success(String message, Object object) {
		return JsonResult.success(HttpStatus.OK, message, object);
	}
	
	public static ResponseEntity<?> success(Object object) {
		return JsonResult.success(HttpStatus.OK, null, object);
	}
	
	public static ResponseEntity<?> success() {
		return JsonResult.success(HttpStatus.OK, "true", "성공");
	}
	
	/**
	 * 실패응답
	 * @param httpStatus
	 * @param message
	 * @param object
	 * @return
	 */
	public static ResponseEntity<?> fail(HttpStatus httpStatus, String message, Object object) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", JsonResult.RESULT_FALSE);
		map.put("message", message);
		map.put("data", object);
		
		return ResponseEntity.status(httpStatus).body(map);
	}
	
	public static ResponseEntity<?> fail(HttpStatus httpStatus, Object object) {
		return JsonResult.fail(httpStatus, null, object);
	}
	
	public static ResponseEntity<?> fail(String message, Object object) {
		return JsonResult.fail(HttpStatus.BAD_REQUEST, message, object);
	}
	
	public static ResponseEntity<?> fail(String message) {
		return JsonResult.fail(HttpStatus.BAD_REQUEST, message, null);
	}
	
	public static ResponseEntity<?> fail() {
		return JsonResult.fail(HttpStatus.BAD_REQUEST, "false", "실패");
	}
}