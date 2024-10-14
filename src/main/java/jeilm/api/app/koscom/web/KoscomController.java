package jeilm.api.app.koscom.web;

import java.net.URI;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jeilm.api.app.koscom.service.KoscomClient;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class KoscomController {

	@Value("${global.koscom.url}")
	private String koscom_url;
	
	@Value("${global.koscom.code}")
	private String code;
	
	@Value("${global.koscom.auth.key}")
	private String auth_key;
	
	@Value("${global.koscom.gubun}")
	private String gubun;
	
	@Value("${global.koscom.count}")
	private int count;
	
	private final KoscomClient koscomClient;
	
	/**
	 * 종목 정보
	 * @param params
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/v1/ir/listservice/getStockBasic")
	public ResponseEntity<?> getStockBasic(@RequestBody Map<String, Object> params,  HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if (params.get("gubun") != null) {
			gubun = (String)params.get("gubun");
		}
		
		URI koscomUri = URI.create(koscom_url + "/getStockBasic"
				+ "?code=" + code
				+ "&auth_key=" + auth_key
				+ "&gubun=" + gubun);
		
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		return new ResponseEntity<>(koscomClient.getKoscomClient(koscomUri), httpHeaders, HttpStatus.OK);
	}
	
	/**
	 * 종목 호가정보
	 * @param params
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/v1/ir/listservice/getStockHoga")
	public ResponseEntity<?> getStockHoga(@RequestBody Map<String, Object> params,  HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if (params.get("gubun") != null) {
			gubun = (String)params.get("gubun");
		}
		
		URI koscomUri = URI.create(koscom_url + "/getStockHoga"
				+ "?code=" + code
				+ "&auth_key=" + auth_key
				+ "&gubun=" + gubun);
		
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		return new ResponseEntity<>(koscomClient.getKoscomClient(koscomUri), httpHeaders, HttpStatus.OK);
	}
	
	/**
	 * 종목 체결정보
	 * @param params
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/v1/ir/listservice/getStockIntra")
	public ResponseEntity<?> getStockIntra(@RequestBody Map<String, Object> params,  HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if (params.get("gubun") != null) {
			gubun = (String)params.get("gubun");
		}
		
		URI koscomUri = URI.create(koscom_url + "/getStockIntra"
				+ "?code=" + code
				+ "&auth_key=" + auth_key
				+ "&gubun=" + gubun);
		
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		return new ResponseEntity<>(koscomClient.getKoscomClient(koscomUri), httpHeaders, HttpStatus.OK);
	}
	
	/**
	 * 종목 일자별정보
	 * @param params
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/v1/ir/listservice/getStockHist")
	public ResponseEntity<?> getStockHist(@RequestBody Map<String, Object> params,  HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if (params.get("gubun") != null) {
			gubun = (String)params.get("gubun");
		}
		
		if (params.get("count") != null) {
			count = (int)params.get("count");
		}
		
		URI koscomUri = URI.create(koscom_url + "/getStockHist"
				+ "?code=" + code
				+ "&auth_key=" + auth_key
				+ "&gubun=" + gubun
				+ "&count=" + count);
		
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		return new ResponseEntity<>(koscomClient.getKoscomClient(koscomUri), httpHeaders, HttpStatus.OK);
	}
	
	/**
	 * 종목 회원사거래정보
	 * @param params
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/v1/ir/listservice/getStockMember")
	public ResponseEntity<?> getStockMember(@RequestBody Map<String, Object> params,  HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if (params.get("gubun") != null) {
			gubun = (String)params.get("gubun");
		}
		
		URI koscomUri = URI.create(koscom_url + "/getStockMember"
				+ "?code=" + code
				+ "&auth_key=" + auth_key);
		
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		return new ResponseEntity<>(koscomClient.getKoscomClient(koscomUri), httpHeaders, HttpStatus.OK);
	}
	
	/**
	 * 지수정보 - 종목
	 * @param params
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/v1/ir/listservice/getStockInfo")
	public ResponseEntity<?> getStockInfo(@RequestBody Map<String, Object> params,  HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if (params.get("gubun") != null) {
			gubun = (String)params.get("gubun");
		}
		
		URI koscomUri = URI.create(koscom_url + "/getStockInfo"
				+ "?code=" + code
				+ "&auth_key=" + auth_key);
		
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		return new ResponseEntity<>(koscomClient.getKoscomClient(koscomUri), httpHeaders, HttpStatus.OK);
	}
	
	/**
	 * 지수정보 - 인덱스
	 * @param params
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/v1/ir/listservice/getIndexBasic")
	public ResponseEntity<?> getIndexBasic(@RequestBody Map<String, Object> params,  HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if (params.get("gubun") != null) {
			gubun = (String)params.get("gubun");
		}
		
		URI koscomUri = URI.create(koscom_url + "/getIndexBasic"
				+ "?code=" + code
				+ "&auth_key=" + auth_key);
		
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		return new ResponseEntity<>(koscomClient.getKoscomClient(koscomUri), httpHeaders, HttpStatus.OK);
	}
	
	/**
	 * 공시 정보
	 * @param params
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/v1/ir/listservice/getDisInfo")
	public ResponseEntity<?> getDisInfo(@RequestBody Map<String, Object> params,  HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if (params.get("gubun") != null) {
			gubun = (String)params.get("gubun");
		}
		
		if (params.get("count") != null) {
			count = (int)params.get("count");
		}
		
		URI koscomUri = URI.create(koscom_url + "/getDisInfo"
				+ "?code=" + code
				+ "&auth_key=" + auth_key
				+ "&gubun=" + gubun
				+ "&count=" + count);
		
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		return new ResponseEntity<>(koscomClient.getKoscomClient(koscomUri), httpHeaders, HttpStatus.OK);
	}
	
}
