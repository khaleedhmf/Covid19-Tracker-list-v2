package com.ct.covid19Tracker.exceptionHandling;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

	public static final String DEFAULT_ERROR_VIEW = "Error";
	
	
	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultErrorHandler(HttpClientErrorException err, Exception e) throws Exception {
		
		if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
			throw e;
		
		ModelAndView mav = new ModelAndView();
	    mav.addObject("exception", e);
	    mav.addObject("Status COde", err.getStatusCode());
	    mav.setViewName(DEFAULT_ERROR_VIEW);
	    return mav;
		

	}
}
