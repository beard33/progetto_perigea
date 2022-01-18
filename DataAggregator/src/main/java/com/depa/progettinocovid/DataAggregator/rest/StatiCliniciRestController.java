package com.depa.progettinocovid.DataAggregator.rest;

import java.util.Date;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.depa.progettinocovid.DataAggregator.service.StatiCliniciAggregationService;

@RestController
public class StatiCliniciRestController {
	
	@Autowired
	StatiCliniciAggregationService aggregator;
	
	@GetMapping(path = "stati_clinici/{field}")
	public ResponseEntity<Response<Document>> totSingola (@PathVariable String field, @RequestParam Date inizio, @RequestParam Date fine) {
		Document body = aggregator.sommaFiltra(field, inizio, fine);
		
		Response<Document> res = successResponse(String.format(
				"somma del campo %s tra il %s e il %s",
				field,
				inizio.toString(),
				fine.toString()), body);
		return new ResponseEntity<Response<Document>>(res, HttpStatus.OK);
	}
	
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
