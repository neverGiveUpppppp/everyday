package com.example.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

public class WebMvcConfiguration implements WebMvcConfigurer{

	
	/**
	 * 다국어 처리 메세지 소스, 빈으로 등록
	 * @return
	 */
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource =
				new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:config/messages/message"); // messages폴더에 message.properties 파일
		messageSource.setDefaultEncoding("UTF-8"); // 인코딩 타입 디폴트값 설정
		
		return messageSource;
	}
	
	@Override
	public Validator getValidator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}
	
	
	
}
