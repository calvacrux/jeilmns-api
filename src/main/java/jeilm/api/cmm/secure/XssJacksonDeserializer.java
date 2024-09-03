package jeilm.api.cmm.secure;

import java.io.IOException;

import org.springframework.web.util.HtmlUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class XssJacksonDeserializer extends JsonDeserializer<String> {
	
	@Override
	public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
    	if (log.isDebugEnabled()) {
    		log.debug("XssJacksonDeserializer : " + jsonParser.getText() + " >> " + HtmlUtils.htmlEscape(jsonParser.getText()));
    	}
    	
    	return HtmlUtils.htmlEscape(jsonParser.getText());
    }

}
