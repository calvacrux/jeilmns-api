package jeilm.api.cmm.data;

import java.io.IOException;
import java.util.HashMap;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jeilm.api.cmm.util.DataUtil;

public class DataMap extends HashMap<String,Object> {

	private static final long serialVersionUID = 1L;
	
	@Override
	public Object put(String key, Object value) {
		return super.put(key.toLowerCase(), value);
	}
	
	public String getStr(Object key) {
		return DataUtil.toString(this.get(key));
	}
	
	public int getInt(Object key) {
		return DataUtil.toInt(this.get(key));
	}
	
	public static DataMap toData(String json) {
		ObjectMapper objectMapper = new ObjectMapper();
		DataMap dataMap = null;

		if (json != null && json.length() > 0) {
			try {
				dataMap = objectMapper.readValue(json, new TypeReference<DataMap>(){});
			} catch (IOException e) {
				dataMap = null;
			}
		}
		
		if (dataMap == null) {
			dataMap = new DataMap();
		}
		
		return dataMap;
	}
}
