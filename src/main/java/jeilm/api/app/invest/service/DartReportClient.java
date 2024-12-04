package jeilm.api.app.invest.service;

import java.net.URI;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "dartReportClient", url="${global.dart.url.report}")
public interface DartReportClient {
	
	@GetMapping("")
	Object getDartReportClient(URI dartReportUri);
	
	@GetMapping("")
	String getDartReportClientString(URI dartReportUri);
	
	@GetMapping("")
	Object getDartReportClientObject(URI dartReportUri);
}
