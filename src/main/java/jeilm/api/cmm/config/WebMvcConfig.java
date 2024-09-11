package jeilm.api.cmm.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import jeilm.api.cmm.filter.XssFilter;
import jeilm.api.cmm.interceptor.CommonInterceptor;
import jeilm.api.cmm.secure.XssJacksonDeserializer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Autowired
	private CommonInterceptor commonInterceptor;
	
	/**
	 * 인터셉터 설정
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//공통 인터셉터
		registry.addInterceptor(commonInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns("/assets/**")
				.excludePathPatterns("/css/**")
				.excludePathPatterns("/images/**")
				.excludePathPatterns("/js/**")
				.excludePathPatterns("/file/**")
				.excludePathPatterns("/favicon.ico");
	}
	
	/**
	 * XSS 필터 설정
	 * @return
	*/ 
	@Bean
	FilterRegistrationBean<XssFilter> xssFilter() {
		FilterRegistrationBean<XssFilter> registrationBean = new FilterRegistrationBean<XssFilter>();
		registrationBean.setFilter(new XssFilter());
		registrationBean.setOrder(Integer.MIN_VALUE);
		registrationBean.setUrlPatterns(Arrays.asList("/*"));
		
		return registrationBean;
	}
	
	
	/**
	 * JSON XSS 설정
	*/
	@Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
		
		ObjectMapper objectMapper = new Jackson2ObjectMapperBuilder()
				.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .modules(new JavaTimeModule())
                .timeZone("Asia/Seoul")
                .build();
		
		//ObjectMapper objectMapper = jackson2HttpMessageConverter.getObjectMapper();
	    //objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	    //objectMapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
	    	    	    
	    SimpleModule simpleModule = new SimpleModule();
	    
	    // XSS - Json으로 HTML 리턴해야 하므로 임시로 막음
	    // simpleModule.addSerializer(String.class, new XssJacksonSerializer());
	    simpleModule.addDeserializer(String.class, new XssJacksonDeserializer());
	    
	    objectMapper.registerModule(simpleModule).registerModule(new ParameterNamesModule());

	    jackson2HttpMessageConverter.setObjectMapper(objectMapper);

	    converters.add(0, jackson2HttpMessageConverter);
    }
	
}
