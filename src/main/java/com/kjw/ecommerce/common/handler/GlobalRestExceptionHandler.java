package com.kjw.ecommerce.common.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalRestExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity handleRestException(Exception e) {
		log.error(e.getMessage(), e);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("문제가 발생했습니다.");
	}

}
