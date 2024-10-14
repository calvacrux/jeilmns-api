package jeilm.api.app.koscom.service;

import java.net.URI;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "koscomClient", url="${global.koscom.url}")
public interface KoscomClient {

	@GetMapping("")
	Object getKoscomClient(URI koscomUri);
	
}
