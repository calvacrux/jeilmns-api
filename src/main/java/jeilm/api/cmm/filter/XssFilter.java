package jeilm.api.cmm.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class XssFilter implements Filter {

	/**
	 * 모든 Request 에 대해 XSS 필터를 적용한다.
	 * 예외 1 - 리소스파일
	 * 예외 2 - HTML Editor
	 * 예외 3 - 뉴스 게시판 
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String path = ((HttpServletRequest)request).getRequestURI();
		
		if (path.startsWith("/assets/") || path.startsWith("/css/") || path.startsWith("/js/") || path.startsWith("/images/") || path.startsWith("/file/")) {
			chain.doFilter(request, response);
		} else if (path.startsWith("/board/")) {
			chain.doFilter(request, response);	
		} else if (path.startsWith("/history/")) {
			chain.doFilter(request, response);	
		} else {
			if (log.isDebugEnabled()) {
				log.debug("XssFilter doFilter : " + ((HttpServletRequest)request).getRequestURI());
			}
			HttpServletRequest req = (HttpServletRequest)request;
			XssRequestWrapper xssRequestWrapper = new XssRequestWrapper(req);
	        chain.doFilter(xssRequestWrapper, response);
		}
	}

}
