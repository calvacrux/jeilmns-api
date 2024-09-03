package jeilm.api.cmm.util;

import org.springframework.web.util.HtmlUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StringUtil {

	/**
	 * 문자열을 HTML 형식으로 변경
	 * @param input
	 * @return
	 */
	public static String str2html(String input) {

        return input.replaceAll("(\r\n|\r|\n)", "<br/>");
        
    }
	
	public static String htmlEscape(String input) {
		return HtmlUtils.htmlEscape(input);
	}
	
	public static String htmlUnescape(String input) {
		return HtmlUtils.htmlUnescape(input);
	}
	
	public static String getDirFromUnixTime() {
		long unixTime = System.currentTimeMillis();
		return String.valueOf(unixTime).substring(0, 4);
	}
	
	public static String objectToJson(Object input) {
		String resultValue = "";
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			resultValue = mapper.writeValueAsString(input);
		} catch (Exception e) {
			log.error("Error objectToJson:" + e.getMessage());
		}
		
		return resultValue;
	}
	
	public static <T>T jsonToObject(String json, Class<T> toValueType) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(json, toValueType);
		} catch (Exception e) {
			log.error("Error jsonToObject:" + e.getMessage());
			return null;
		}
	}
	
}
