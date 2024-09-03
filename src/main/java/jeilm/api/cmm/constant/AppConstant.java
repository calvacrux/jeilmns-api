package jeilm.api.cmm.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConstant {

	public static String storagePath;
	
	@Value("${global.ncloud.storage.path}")
	public void setStoragePath(String value) {
		AppConstant.storagePath = value;
	}
	
}
