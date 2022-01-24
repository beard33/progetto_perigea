package com.depa.progettinocovid.DataExtractor.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import commons.rest.Response;

@ControllerAdvice
public class ControllerAdvisor {
	
	@ExceptionHandler({
		DateOutOfRangeException.class,
		BadTemaRequestException.class})
	public ResponseEntity<Response<Object>> badRequest(BadRequestException ex) {
		Response<Object> res = Response
				.builder()
				.type(Response.Type.ERROR)
				.description(ex.getMessage())
				.code(HttpStatus.BAD_REQUEST.value())
				.build();
		return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(TemaAlreadyRunningException.class)
	public ResponseEntity<Response<Object>> temaRunningEntity(TemaAlreadyRunningException ex) {
		Response<Object> res = Response.<Object>builder()
				.type(Response.Type.ERROR)
				.code(HttpStatus.CONFLICT.value())
				.description(ex.getMessage())
				.build();
		return new ResponseEntity<>(res, HttpStatus.CONFLICT);
	}
	
}
