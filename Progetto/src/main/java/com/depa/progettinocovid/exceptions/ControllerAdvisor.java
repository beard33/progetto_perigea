package com.depa.progettinocovid.exceptions;
/**
 * ControllerAdvice per la gestione delle eccezioni del
 *  {@link com.depa.progettinocovid.rest.VacciniRestController} e restituzione
 *  di una response uniforme anche in caso di errori
 */


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.depa.progettinocovid.models.Provincia;
import com.depa.progettinocovid.rest.Response;
import com.mongodb.MongoSocketException;

@ControllerAdvice
public class ControllerAdvisor {

	@ExceptionHandler(ProvinciaNotFoundException.class)
	public ResponseEntity<Response<Provincia>> ProvinciaNotFound(ProvinciaNotFoundException ex) {
		Response<Provincia> res = Response
					.<Provincia>builder()
					.type(Response.Type.ERROR)
					.description(ex.getMessage())
					.code(HttpStatus.NOT_FOUND.value())
					.build();
		return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
	}
		
	@ExceptionHandler(MongoSocketException.class)
	public ResponseEntity<Response<Provincia>> MongoInternalException(){
		Response<Provincia> res = Response
				.<Provincia>builder()
				.type(Response.Type.ERROR)
				.description("Internal error")
				.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.build();
		
		return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
