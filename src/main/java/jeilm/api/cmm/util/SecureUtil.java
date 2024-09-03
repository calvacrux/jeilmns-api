package jeilm.api.cmm.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SecureUtil {

	public static final String UPLOAD_EXT = "uploadExt";
	public static final String UPLOAD_DET = "uploadDet";
	public static final String DOWNLOAD_EXT = "downloadExt";
	
	public static String makeSecureString(String str, int maxLength) {
		Pattern unsecuredCharPattern = Pattern.compile("[^\\p{Alnum}]|select|delete|update|insert|create|alter|drop", 2);

		String secureStr = str.substring(0, maxLength);
		Matcher matcher = unsecuredCharPattern.matcher(secureStr);
		return matcher.replaceAll("");
	}

	public static String clearXSSMinimum(String value) {
		if ((value == null) || (value.trim().equals(""))) {
			return "";
		}
		String returnValue = value;

		returnValue = returnValue.replaceAll("&", "&amp;");
		returnValue = returnValue.replaceAll("<", "&lt;");
		returnValue = returnValue.replaceAll(">", "&gt;");
		returnValue = returnValue.replaceAll("\"", "&#34;");
		returnValue = returnValue.replaceAll("'", "&#39;");

		return returnValue;
	}

	public static String clearXSSMaximum(String value) {
		String returnValue = value;

		returnValue = clearXSSMinimum(returnValue);
		returnValue = returnValue.replaceAll("%00", null);
		returnValue = returnValue.replaceAll("%", "&#37;");
		returnValue = returnValue.replaceAll("\\.\\./", "");
		returnValue = returnValue.replaceAll("\\.\\.\\\\", "");
		returnValue = returnValue.replaceAll("\\./", "");
		returnValue = returnValue.replaceAll("%2F", "");

		return returnValue;
	}

	public static String filePathBlackList(String value) {
		String returnValue = value;
		if ((returnValue == null) || (returnValue.trim().equals(""))) {
			return "";
		}
		returnValue = returnValue.replaceAll("\\.\\./", "");
		returnValue = returnValue.replaceAll("\\.\\.\\\\", "");

		return returnValue;
	}

	public static String filePathReplaceAll(String value) {
		String returnValue = value;
		if ((returnValue == null) || (returnValue.trim().equals(""))) {
			return "";
		}
		returnValue = returnValue.replaceAll("/", "");
		returnValue = returnValue.replaceAll("\\", "");
		returnValue = returnValue.replaceAll("\\.\\.", "");
		returnValue = returnValue.replaceAll("&", "");

		return returnValue;
	}

	public static String makeSecureStringLDAP(String str) {
		Pattern unsecuredCharPattern = Pattern.compile("[^\\p{Alnum}]", 2);

		Matcher matcher = unsecuredCharPattern.matcher(str);
		return matcher.replaceAll("");
	}

	public static String filePathWhiteList(String value) {
		return value;
	}

	public static boolean isIPAddress(String str) {
		Pattern ipPattern = Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");

		return ipPattern.matcher(str).matches();
	}

	public static String removeCRLF(String parameter) {
		return parameter.replaceAll("\r", "").replaceAll("\n", "");
	}

	public static String removeSQLInjectionRisk(String parameter) {
		return parameter.replaceAll("\\p{Space}", "")
						.replaceAll("\\*", "")
						.replaceAll("%", "")
						.replaceAll(";", "")
						.replaceAll("-", "")
						.replaceAll("\\+", "")
						.replaceAll(",", "");
	}

	public static String removeOSCmdRisk(String parameter) {
		return parameter.replaceAll("\\p{Space}", "")
						.replaceAll("\\*", "")
						.replaceAll("|", "")
						.replaceAll(";", "");
	}

	public static boolean passwordValidation(String str) {
		boolean result = true;
		if ((str != null) && (str.trim().length() < 9)) {
			return false;
		}
		Pattern expression1 = Pattern.compile("[0-9]");
		Matcher matcher1 = expression1.matcher(str);

		Pattern expression2 = Pattern.compile("[^\\p{Alnum}]");
		Matcher matcher2 = expression2.matcher(str);

		Pattern expression3 = Pattern.compile("[a-zA-Z]");
		Matcher matcher3 = expression3.matcher(str);
		if (!matcher1.find()) {
			return false;
		}
		if (!matcher2.find()) {
			return false;
		}
		if (!matcher3.find()) {
			return false;
		}
		return result;
	}
	
	public static HashMap<String, String> uploadFileExtCheck(String fileName, String param) {
		Pattern pattern = null;
		HashMap<String, String> result = new HashMap<String, String>();
		if (param.equals("uploadExt")) {
			pattern = Pattern.compile("([^\\s]+(\\.(?i)(asp|aspx|asa|cdx|cer|htr|jsp|jspx|jsw|jsv|jspf|jar|java|war|cgi|exe|inc|phtml|php|php3|php4|php5|htm|html|hta|htx|mhtm|mhtml|mht|shtml|chm)){1,})");
		} else if (param.equals("uploadDetour")) {
			pattern = Pattern.compile("(\\%00|\\%ZZ|\\%0a|\\%2E|\\%2B|\\%5C|\\%20|\\%22|\\%70|\\%3f|#|\\:\\:){1,}");
		}
		Matcher matcher = pattern.matcher(fileName);
		if (matcher.find()) {
			result.put("result", "true");
			result.put("securitySort", "FileUpload");
			result.put("violationChar", matcher.group());
		}
		return result;
	}

	public static String[] convertXSSParams(String[] values) {
		StringBuffer strBuff = new StringBuffer();
		for (int i = 0; i < values.length; i++) {
			if (values[i] != null) {
				for (int j = 0; j < values[i].length(); j++) {
					char c = values[i].charAt(j);
					switch (c) {
					case '\'':
						strBuff.append("&#39;");
						break;
					case '"':
						strBuff.append("&quot;");
						break;
					case ':':
						strBuff.append("&#58;");
						break;
					case ';':
						strBuff.append("&#59;");
						break;
					case '(':
						strBuff.append("&#40;");
						break;
					case ')':
						strBuff.append("&#41;");
						break;
					case '<':
						strBuff.append("&lt;");
						break;
					case '>':
						strBuff.append("&gt;");
						break;
					case '{':
						strBuff.append("&#123;");
						break;
					case '}':
						strBuff.append("&#125;");
						break;
					case '#':
						strBuff.append("&#35;");
						break;
					case '$':
						strBuff.append("&#36;");
						break;
					case '%':
						strBuff.append("&#37;");
						break;
					case '&':
						strBuff.append("&amp;");
						break;
					case '?':
						strBuff.append("&#63;");
						break;
					case '!':
						strBuff.append("&#33;");
						break;
					case '@':
						strBuff.append("&#64;");
						break;
					case '*':
						strBuff.append("&#42;");
						break;
					case '|':
						strBuff.append("&#124;");
						break;
					default:
						strBuff.append(c);
					}
				}
				values[i] = strBuff.toString();
				values[i] = reConvertXssString(values[i]);
				strBuff.setLength(0);
			} else {
				values[i] = null;
			}
		}
		return values;
	}

	public static String convertXSSParam(String value) {
		StringBuffer strBuff = new StringBuffer();
		for (int i = 0; i < value.length(); i++) {
			char c = value.charAt(i);
			switch (c) {
			case '\'':
				strBuff.append("&#39;");
				break;
			case '"':
				strBuff.append("&quot;");
				break;
			case ':':
				strBuff.append("&#58;");
				break;
			case ';':
				strBuff.append("&#59;");
				break;
			case '(':
				strBuff.append("&#40;");
				break;
			case ')':
				strBuff.append("&#41;");
				break;
			case '<':
				strBuff.append("&lt;");
				break;
			case '>':
				strBuff.append("&gt;");
				break;
			case '{':
				strBuff.append("&#123;");
				break;
			case '}':
				strBuff.append("&#125;");
				break;
			case '#':
				strBuff.append("&#35;");
				break;
			case '$':
				strBuff.append("&#36;");
				break;
			case '%':
				strBuff.append("&#37;");
				break;
			case '&':
				strBuff.append("&amp;");
				break;
			case '?':
				strBuff.append("&#63;");
				break;
			case '!':
				strBuff.append("&#33;");
				break;
			case '@':
				strBuff.append("&#64;");
				break;
			case '*':
				strBuff.append("&#42;");
				break;
			case '|':
				strBuff.append("&#124;");
				break;
			default:
				strBuff.append(c);
			}
		}
		value = strBuff.toString();
		value = reConvertXssString(value);
		return value;
	}

	public static String convertXSSJsonParam(String value) {
		StringBuffer strBuff = new StringBuffer();
		for (int i = 0; i < value.length(); i++) {
			char c = value.charAt(i);
			switch (c) {
			case '\'':
				strBuff.append("&#39;");
				break;
			case ';':
				strBuff.append("&#59;");
				break;
			case '(':
				strBuff.append("&#40;");
				break;
			case ')':
				strBuff.append("&#41;");
				break;
			case '<':
				strBuff.append("&lt;");
				break;
			case '>':
				strBuff.append("&gt;");
				break;
			case '#':
				strBuff.append("&#35;");
				break;
			case '$':
				strBuff.append("&#36;");
				break;
			case '%':
				strBuff.append("&#37;");
				break;
			case '&':
				strBuff.append("&amp;");
				break;
			case '?':
				strBuff.append("&#63;");
				break;
			case '!':
				strBuff.append("&#33;");
				break;
			case '@':
				strBuff.append("&#64;");
				break;
			case '*':
				strBuff.append("&#42;");
				break;
			case '|':
				strBuff.append("&#124;");
				break;
			default:
				strBuff.append(c);
			}
		}
		value = strBuff.toString();
		value = reConvertXssString(value);
		return value;
	}

	public static String reConvertXssString(String checkValue) {
		String value = checkValue;
		if (value.indexOf("&amp;&#35;39&#59;") > -1) {
			value = value.replaceAll("&amp;&#35;39&#59;", "&#39;");
		}
		if (value.indexOf("&amp;quot&#59;") > -1) {
			value = value.replaceAll("&amp;quot&#59;", "&quot;");
		}
		if (value.indexOf("&amp;&#35;58&#59;") > -1) {
			value = value.replaceAll("&amp;&#35;58&#59;", "&#58;");
		}
		if (value.indexOf("&amp;&#35;59&#59;") > -1) {
			value = value.replaceAll("&amp;&#35;59&#59;", "&#59;");
		}
		if (value.indexOf("&amp;&#35;40&#59;") > -1) {
			value = value.replaceAll("&amp;&#35;40&#59;", "&#40;");
		}
		if (value.indexOf("&amp;&#35;41&#59;") > -1) {
			value = value.replaceAll("&amp;&#35;41&#59;", "&#41;");
		}
		if (value.indexOf("&amp;lt&#59;") > -1) {
			value = value.replaceAll("&amp;lt&#59;", "&lt;");
		}
		if (value.indexOf("&amp;gt&#59;") > -1) {
			value = value.replaceAll("&amp;gt&#59;", "&gt;");
		}
		if (value.indexOf("&amp;&#35;123&#59;") > -1) {
			value = value.replaceAll("&amp;&#35;123&#59;", "&#123;");
		}
		if (value.indexOf("&amp;&#35;125&#59;") > -1) {
			value = value.replaceAll("&amp;&#35;125&#59;", "&#125;");
		}
		if (value.indexOf("&amp;&#35;35&#59;") > -1) {
			value = value.replaceAll("&amp;&#35;35&#59;", "&#35;");
		}
		if (value.indexOf("&amp;&#35;36&#59;") > -1) {
			value = value.replaceAll("&amp;&#35;36&#59;", "&#36;");
		}
		if (value.indexOf("&amp;&#35;37&#59;") > -1) {
			value = value.replaceAll("&amp;&#35;37&#59;", "&#37;");
		}
		if (value.indexOf("&amp;amp&#59;") > -1) {
			value = value.replaceAll("&amp;amp&#59;", "&amp;");
		}
		if (value.indexOf("&amp;&#35;63&#59;") > -1) {
			value = value.replaceAll("&amp;&#35;63&#59;", "&#63;");
		}
		if (value.indexOf("&amp;&#35;33&#59;") > -1) {
			value = value.replaceAll("&amp;&#35;33&#59;", "&#33;");
		}
		if (value.indexOf("&amp;&#35;64&#59;") > -1) {
			value = value.replaceAll("&amp;&#35;64&#59;", "&#64;");
		}
		if (value.indexOf("&amp;&#35;42&#59;") > -1) {
			value = value.replaceAll("&amp;&#35;42&#59;", "&#42;");
		}
		if (value.indexOf("&amp;&#35;124&#59;") > -1) {
			value = value.replaceAll("&amp;&#35;124&#59;", "&#124;");
		}
		return value;
	}

	public static HashMap<String, String> checkXSSParams(String values) {
		HashMap<String, String> result = new HashMap<String, String>();
		Pattern pattern = Pattern.compile("(?i)('|\"|:|;|\\(|\\)|<|>|\\[|\\]|\\{|\\}|`|=|#|\\$|%|&|\\?|!|@|\\*|\t|\\||%27|%22|%3a|%3b|%28|%29|%3c|%3e|%5b|%5d|%7b|%7d|%60|%3d|%23|%24|%25|%26|%3f|%21|%40|%2a|%09|%7c|&#x|27;|&#x22;|&#x3a;|&#x3b;|&#x28;|&#x29;|&#x3c;|&#x3e;|&#x5b;|&#x5d;|&#x7b;|&#x7d;|&#x60;|&#x3d;|&#x23;|&#x24;|&#x25;|&#x26;|&#x3f;|&#x21;|&#x40;|&#x2a;|&#x09;|&#x7c;|script|javascript|vbscript|livescript|iframe|mocha|applet|img|embed|object|marquee|qss|body|input|form|div|style|table|isindex|meta|http-equiv|xss|href){1,}");

		Matcher matcher = pattern.matcher(values);
		if (matcher.find()) {
			result.put("result", "true");
			result.put("securitySort", "XSS");
			result.put("violationChar", matcher.group());
		}
		return result;
	}

	public static HashMap<String, String> checkSQLInjectionParams(String values) {
		HashMap<String, String> result = new HashMap<String, String>();
		Pattern pattern = Pattern.compile("(?i)('|\"|:|;|\\(|\\)|<|>|\\[|\\]|\\{|\\}|`|=|#|\\$|%|&|\\?|!|@|\\*|\t|\\||%27|%22|%3a|%3b|%28|%29|%3c|%3e|%5b|%5d|%7b|%7d|%60|%3d|%23|%24|%25|%26|%3f|%21|%40|%2a|%09|%7c|&#x|27;|&#x22;|&#x3a;|&#x3b;|&#x28;|&#x29;|&#x3c;|&#x3e;|&#x5b;|&#x5d;|&#x7b;|&#x7d;|&#x60;|&#x3d;|&#x23;|&#x24;|&#x25;|&#x26;|&#x3f;|&#x21;|&#x40;|&#x2a;|&#x09;|&#x7c;|select|union|order by|where|join|create|drop|update|alter|from|and|or|asc|delay|return|instance|version|colnum|declare|then|if|else|end|exec|all|into|null|super|schema|case|case|desc|waitfor|table|having|banner|rownum|varchar|sleep\\(\\)|chr\\(\\)|ascii\\(\\)|substr\\(\\)|bitand\\(\\)|lower\\(\\)|concat\\(\\)|count\\(\\)|distinct\\(\\)|database\\(\\)|end\\(\\)|asciistr\\(\\)|instr\\(\\)|length\\(\\)|tochar\\(\\)){1,}");

		Matcher matcher = pattern.matcher(values);
		if (matcher.find()) {
			result.put("result", "true");
			result.put("securitySort", "SQL Injection");
			result.put("violationChar", matcher.group());
		}
		return result;
	}

	public static HashMap<String, String> checkDownloadParams(String values) throws UnsupportedEncodingException {
		Pattern checkPattern = Pattern.compile("(%\\p{Alnum}{1}\\p{Alnum}{1}){1,}");

		Matcher m = checkPattern.matcher(values);
		if (m.find()) {
			values = URLDecoder.decode(values, "utf-8");
			values = URLDecoder.decode(values, "utf-8");
		}
		HashMap<String, String> result = new HashMap<String, String>();
		Pattern pattern = Pattern.compile("(\\.\\.\\\\|\\.\\.\\/|\\.\\.\\.\\.\\/\\/|\\.\\.\\.\\/\\.\\/){1,}");

		Matcher matcher = pattern.matcher(values);
		if (matcher.find()) {
			result.put("result", "true");
			result.put("securitySort", "File Download");
			result.put("violationChar", matcher.group());
		}
		return result;
	}
	
}
