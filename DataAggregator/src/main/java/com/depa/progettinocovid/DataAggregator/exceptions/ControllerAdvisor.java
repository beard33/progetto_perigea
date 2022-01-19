package com.depa.progettinocovid.DataAggregator.exceptions;
/**
 * ControllerAdvice per la gestione delle eccezioni del
 *  {@link com.depa.progettinocovid.rest.VacciniRestController} e restituzione
 *  di una response uniforme anche in caso di errori
 */


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mongodb.MongoSocketException;

import commons.rest.Response;

// TODO era legato a Provincia senza motivo
@ControllerAdvice
public class ControllerAdvisor {

	@ExceptionHandler(ProvinciaNotFoundException.class)
	public ResponseEntity<Response<Object>> ProvinciaNotFound(ProvinciaNotFoundException ex) {
		Response<Object> res = Response
					.builder()
					.type(Response.Type.ERROR)
					.description(ex.getMessage())
					.code(HttpStatus.NOT_FOUND.value())
					.build();
		return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
	}
		
	@ExceptionHandler(MongoSocketException.class)
	public ResponseEntity<Response<Object>> MongoInternalException(){
		Response<Object> res = Response
				.builder()
				.type(Response.Type.ERROR)
				.description("Internal error")
				.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.build();
		
		return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
