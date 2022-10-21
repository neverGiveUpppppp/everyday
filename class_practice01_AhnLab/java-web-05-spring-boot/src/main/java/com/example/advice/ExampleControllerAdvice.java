package com.example.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExampleControllerAdvice {

	final Logger logger = LoggerFactory.getLogger(getClass());
	
	
	/**
	 * Exception 발생에 대한 예외처리
	 * @param e
	 * @return
	 */
	public ModelAndView handleException(Exception e) {
		logger.error("handleException",e);
		ModelAndView model = new ModelAndView("/error/error.html");
		model.addObject("exception", e);
		return model;
		
	}
	
}
