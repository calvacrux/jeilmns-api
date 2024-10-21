package jeilm.api.app.certificate.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jeilm.api.app.certificate.service.CertificateService;
import jeilm.api.app.certificate.vo.CertificateVO;
import jeilm.api.cmm.json.JsonResult;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CertificateController {
	
	private final CertificateService certificateService;
	
	/**
	 * 인증서 리스트
	 * @param certificateVO
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/v1/certificate/list")
	public ResponseEntity<?> getHistoryList(@RequestBody CertificateVO certificateVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 아이디
		if (certificateVO.getLang().equals("ko")) {
			certificateVO.setCertificate_id("cert.ko");
		} else if (certificateVO.getLang().equals("en")) {
			certificateVO.setCertificate_id("cert.en");
		} else if (certificateVO.getLang().equals("cn")) {
			certificateVO.setCertificate_id("cert.cn");
		} else {
			return JsonResult.fail("언어설정이 올바르지 않습니다.");
		}
		
		List<CertificateVO> resultList = certificateService.selectCertificateList(certificateVO);
		
		map.put("certificate_list", resultList);
		
		return JsonResult.success(map);		
	}

}
