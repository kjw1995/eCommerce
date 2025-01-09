package com.kjw.ecommerce.common.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.kjw.ecommerce.common.exception.DataListFetchException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception e) {
		log.error(e.getMessage(), e);
		return new ModelAndView("/error/404");
	}

	@ExceptionHandler(DataListFetchException.class)
	public ModelAndView handleDataListFetchException(DataListFetchException dle) {
		log.error(dle.getMessage(), dle);
		return new ModelAndView("/error/404");
	}

}
