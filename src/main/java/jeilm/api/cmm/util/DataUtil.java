package jeilm.api.cmm.util;

public class DataUtil {

	public static String toString(Object obj) {
		
		if (obj == null) {
			return "";
		}
		
		if (obj instanceof String) {
			return (String)obj;
		}
		
		return obj.toString();
	}
	
	public static int toInt(Object obj, int defVal) {
		if (obj == null) {
			return defVal;
		}			

		if (obj instanceof Integer) {
			return (Integer)obj;
		}			

		if (obj instanceof Double) {
			return ((Double)obj).intValue();
		}

		if (obj instanceof Float) {
			return ((Float)obj).intValue();
		}

		int retrunValue = defVal;

		try {
			String target = obj.toString();
			retrunValue = Integer.parseInt(target.trim());
		} catch (NumberFormatException e) { 
			retrunValue = defVal; 
		}

		return retrunValue;
	}
	
	public static int toInt(Object obj) {
		return toInt(obj, 0);
	}
}
