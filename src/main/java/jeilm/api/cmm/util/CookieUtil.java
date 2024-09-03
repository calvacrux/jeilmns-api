package jeilm.api.cmm.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CookieUtil {

	private static final String encoding = "UTF-8";
	private static final String path = "/";
	
	/**
	 * 특정 key의 쿠키값을 List로 반환한다.
	 * @param key
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public List<String> getValueList(String key, HttpServletRequest request) throws UnsupportedEncodingException {
		Cookie[] cookies = request.getCookies();
		String[] cookieValues = null;
		String value = "";
		List<String> list = null;

		// 특정 key의 쿠키값을 ","로 구분하여 String 배열에 담아준다.
		// ex) 쿠키의 key/value ---> key = "clickItems", value = "211, 223, 303"(상품 번호)
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals(key)) {
					value = cookies[i].getValue();
					cookieValues = (URLDecoder.decode(value, encoding)).split(",");
					break;
				}
			}
		}

		// String 배열에 담겼던 값들을 List로 다시 담는다.
		if (cookieValues != null) {
			list = new ArrayList<String>(Arrays.asList(cookieValues));

			if (list.size() > 10) { // 값이 10개를 초과하면, 최근 것 10개만 담는다.
				int start = list.size() - 10;
				List<String> copyList = new ArrayList<String>();
				for (int i = start; i < list.size(); i++) {
					copyList.add(list.get(i));
				}
				list = copyList;
			}
		}
		return list;
	}
	
	/**
	 * 쿠키를 생성 혹은 업데이트한다.
	 * @param key
	 * @param value
	 * @param day
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	public void setCookie(String key, String value, int day, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		List<String> list = getValueList(key, request);
		String sumValue = "";
		int equalsValueCnt = 0;

		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				sumValue += list.get(i) + ",";
				if (list.get(i).equals(value)) {
					equalsValueCnt++;
				}
			}
			if (equalsValueCnt != 0) { // 같은 값을 넣으려고 할 때의 처리
				if (sumValue.substring(sumValue.length() - 1).equals(",")) {
					sumValue = sumValue.substring(0, sumValue.length() - 1);
				}
			} else {
				sumValue += value;
			}
		} else {
			sumValue = value;
		}

		if (!sumValue.equals("")) {
			Cookie cookie = new Cookie(key, URLEncoder.encode(sumValue, encoding));
			cookie.setMaxAge(60 * 60 * 24 * day);
			cookie.setPath(path);
			response.addCookie(cookie);
		}
	}
	
	/**
	 * 쿠키값들 중 특정 값을 삭제한다.
	 * @param key
	 * @param value
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	public void deleteCookie(String key, String value, HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		List<String> list = getValueList(key, request);
		list.remove(value);

		String sumValue = "";
		if (list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				sumValue += list.get(i) + ",";
			}
			if (sumValue.substring(sumValue.length() - 1).equals(",")) {
				sumValue = (sumValue.substring(0, sumValue.length() - 1)).replaceAll(" ", "");
			}
		}
		Cookie cookie = null;
		int time = 60 * 60 * 24 * 20;

		if (sumValue.equals("")) {
			time = 0;
		}

		cookie = new Cookie(key, URLEncoder.encode(sumValue, encoding));
		cookie.setMaxAge(time);
		cookie.setPath(path);
		response.addCookie(cookie);
	}
	
	/**
	 * 일반적인 쿠키 생성
	 * @param key
	 * @param value
	 * @param day
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public Cookie createNewCookie(String key, String value, int day, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Cookie cookie = new Cookie(key, URLEncoder.encode(value, encoding));
		cookie.setPath(path);
		cookie.setMaxAge(60 * 60 * 24 * day);
		response.addCookie(cookie);
		return cookie;
	}
	
	/**
	 * 쿠키들을 맵으로 반환한다.
	 * @param request
	 * @return
	 */
	public HashMap<String, Cookie> getValueMap(HttpServletRequest request) {
		HashMap<String, Cookie> cookieMap = new HashMap<String, Cookie>();

		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				cookieMap.put(cookies[i].getName(), cookies[i]);
			}
		}

		return cookieMap;
	}
	
	/**
	 * ","로 구분된 값이 아닌 단일 값으로 저장된 쿠키의 값을 반환한다.
	 * @param key
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String getValue(String key, HttpServletRequest request) throws UnsupportedEncodingException {
		Cookie cookie = (Cookie) getValueMap(request).get(key);
		if (cookie == null)
			return null;
		return URLDecoder.decode(cookie.getValue(), encoding);
	}
	
	/**
	 * 쿠키가 있는지 확인.
	 * @param key
	 * @param request
	 * @return
	 */
	public boolean isExist(String key, HttpServletRequest request) {
		return getValueMap(request).get(key) != null;
	}
	
}
