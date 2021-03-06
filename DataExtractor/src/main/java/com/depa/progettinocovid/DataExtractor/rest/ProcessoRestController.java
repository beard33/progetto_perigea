package com.depa.progettinocovid.DataExtractor.rest;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.depa.progettinocovid.DataExtractor.exceptions.DateOutOfRangeException;
import com.depa.progettinocovid.DataExtractor.model.ProcessoDto;
import com.depa.progettinocovid.DataExtractor.service.ProcessoService;

import commons.rest.Response;

/** espone due endpoint /processi e /processi?giorno=yyyy-MM-dd per visualizzare tutti i processi oppure solo quelli iniziati in un dato giorno
 * */
@RestController
public class ProcessoRestController {
	
	@Autowired
	private ProcessoService processoService;
	
	private static Date today = new Date();
	
	// Restituire tutti i processi presenti nel DB
	@GetMapping(path = "processi")
	public ResponseEntity<Response<List<ProcessoDto>>> listaProcessi () {
		List<ProcessoDto> lista = processoService.findAll();
		
		Response<List<ProcessoDto>> res = successResponse("Tutti i processi registrati", lista);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	// Restituire tutti i processi presenti nel DB
//	richiede il formato YYYY-MM-DD
	@GetMapping(path = "processi", params = {"giorno"})
	public ResponseEntity<Response<List<ProcessoDto>>> listaProcessi (
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date giorno) throws ParseException {
		
		if (today.before(giorno)) {
			throw new DateOutOfRangeException(giorno);
		}
		
		List<ProcessoDto> lista = processoService.findByDate(giorno);
		Response<List<ProcessoDto>> res = successResponse("Tutti i processi registrati il " + giorno, lista);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
//	TODO estrarre, possibilmente in Response
	private <T> Response<T> successResponse(String desc, T body) {
		Response<T> response = Response.<T>builder()
				.type(Response.Type.SUCCESS)
				.code(HttpStatus.OK.value())
				.description(desc)
				.body(body)
				.build();
		return response;
	}
}
