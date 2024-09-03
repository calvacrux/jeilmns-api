package jeilm.api.cmm.filter;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.web.util.HtmlUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

public class XssRequestWrapper extends HttpServletRequestWrapper {

	public XssRequestWrapper(HttpServletRequest request) {
		super(request);
	}
	
	@Override
    public String getQueryString() {
        String value = super.getQueryString();
        
        if (value != null) {
        	value = HtmlUtils.htmlEscape(value);
        }
        
        return value;
    }

    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        
		if (value != null) { 
			value = HtmlUtils.htmlEscape(value); 
		}
		        
        return value;
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        if (ArrayUtils.isEmpty(values)) {
            return values;
        }
        int length = values.length;
        String[] escapeValues = new String[length];
        for (int i = 0; i < length; i++) {
            String value = values[i];
            if (value != null) { 
            	escapeValues[i] = HtmlUtils.htmlEscape(value); 
    		} else {
    			escapeValues[i] = value;
    		}
        }
        return escapeValues;
    }

}
