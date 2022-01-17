package com.depa.progettinocovid.DataExtractor.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.support.MethodArgumentTypeMismatchException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.depa.progettinocovid.DataExtractor.rest.Response;

@ControllerAdvice
public class ControllerAdvisor {

	@ExceptionHandler({DateOutOfRangeException.class,MethodArgumentTypeMismatchException.class})
	public ResponseEntity<Response<Object>> DateOutOfRange(DateOutOfRangeException ex) {
		Response<Object> res = Response
				.builder()
				.type(Response.Type.ERROR)
				.description(ex.getMessage())
				.code(HttpStatus.BAD_REQUEST.value())
				.build();
		return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
	}
	
}
