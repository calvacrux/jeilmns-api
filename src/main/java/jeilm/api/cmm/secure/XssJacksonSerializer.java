package jeilm.api.cmm.secure;

import java.io.IOException;

import org.springframework.web.util.HtmlUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class XssJacksonSerializer extends JsonSerializer<String> {
	
	@Override
    public void serialize(String s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
    	jsonGenerator.writeString(HtmlUtils.htmlEscape(s));
    }
}
