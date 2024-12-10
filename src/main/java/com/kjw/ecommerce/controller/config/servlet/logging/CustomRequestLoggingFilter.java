package com.kjw.ecommerce.controller.config.servlet.logging;

import org.springframework.web.filter.CommonsRequestLoggingFilter;

import jakarta.servlet.http.HttpServletRequest;

public class CustomRequestLoggingFilter extends CommonsRequestLoggingFilter {

	private static final String JS = "/js/";
	private static final String CSS = "/css/";
	private static final String JPG = "/jpg/";
	private static final String IMAGES = "/images/";

	@Override
	protected boolean shouldLog(HttpServletRequest request) {
		String uri = request.getRequestURI();
		return !(uri.startsWith(JS) || uri.startsWith(CSS) || uri.startsWith(JPG) || uri.startsWith(IMAGES));
	}

}
