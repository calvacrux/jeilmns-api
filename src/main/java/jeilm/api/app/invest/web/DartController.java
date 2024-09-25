package jeilm.api.app.invest.web;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
import jeilm.api.app.invest.service.DartReportClient;
import jeilm.api.app.invest.vo.DartVO;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DartController {

	@Value("${global.dart.url.report}")
	private String url_report;
	
	@Value("${global.dart.api.key}")
	private String crtfc_key;
	
	@Value("${global.dart.corp.code}")
	private String corp_code;
	
	@Value("${global.dart.corp.cls}")
	private String corp_cls;
	
	@Value("${global.dart.start}")
	private String bgn_de;
	
	private final DartReportClient dartReportClient;
	
	/**
	 * 공시정보 리스트 - Dart
	 * @param dartVO
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/v1/invest/dart/list")
	public ResponseEntity<?> getDartReportList (@RequestBody DartVO dartVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String end_de = LocalDate.now().format( DateTimeFormatter.BASIC_ISO_DATE);
		
		URI dartReportUri = URI.create(url_report 
				+ "?crtfc_key=" + crtfc_key 
				+ "&corp_code=" + corp_code 
				+ "&corp_cls=" + corp_cls 
				+ "&bgn_de=" + bgn_de 
				+ "&end_de=" + end_de
				+ "&page_no=" + dartVO.getPage_no()
				+ "&page_count=" + dartVO.getPage_count());
		
		final HttpHeaders httpHeaders= new HttpHeaders();
	    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		return new ResponseEntity<>(dartReportClient.getDartReportClient(dartReportUri), httpHeaders, HttpStatus.OK);
		
	}
}
