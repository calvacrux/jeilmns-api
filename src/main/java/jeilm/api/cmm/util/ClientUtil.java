package jeilm.api.cmm.util;

import jakarta.servlet.http.HttpServletRequest;

public class ClientUtil {

	/**
	 * 사용자 IP를 가져온다.
	 * @param request
	 * @return
	 */
	public static String getClientIp(HttpServletRequest request) {
		String clientIp = request.getHeader("Proxy-Client-IP");
		if (clientIp == null) {
			clientIp = request.getHeader("WL-Proxy-Client-IP");
			if (clientIp == null) {
				clientIp = request.getHeader("X-Forwarded-For");
				if (clientIp == null) {
					clientIp = request.getRemoteAddr();
				}
			}
		}
		return clientIp;
	}
	
	public static String getUrlPattern(HttpServletRequest request) {
		String urlPattern = request.getRequestURI();
		
		if (urlPattern.lastIndexOf("/") == 0) {
			urlPattern = "/dashboard";
		} else {
			urlPattern = urlPattern.substring(0, urlPattern.lastIndexOf("/")) + "/**";
		}
		
		return urlPattern;		
	}
	
}
