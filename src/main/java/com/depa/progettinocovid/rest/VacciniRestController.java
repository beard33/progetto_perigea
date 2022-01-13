package com.depa.progettinocovid.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.depa.progettinocovid.service.ConteggioAggregationService;
import com.depa.progettinocovid.service.ConteggioAggregationService.Capo;

@RestController
public class VacciniRestController {
	
	@Autowired
	ConteggioAggregationService aggregator;
	
	// Restituire il numero totale della regione lombardia dei vaccinati con singola dose
	@GetMapping(path = "tot_singola")
	public ResponseEntity<Response<AggregationResults<Object>>> totSingola () {
		AggregationResults<Object> body = aggregator.somma("dose1");
		
		Response<AggregationResults<Object>> res = successResponse(
				"Totale una dose", body);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	// Restituire il numero totale della regione lombardia dei vaccinati con doppia dose 
	@GetMapping(path = "tot_doppia")
	public ResponseEntity<Response<AggregationResults<Object>>> totDoppia () {
		AggregationResults<Object> body = aggregator.somma("dose2");
		
		Response<AggregationResults<Object>> res = successResponse(
				"Totale due dosi", body);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	// Restituire il numero totale della regione lombardia dei vaccinati con singola dose per provincia
	@GetMapping(path = "tot_singola/prov")
	public ResponseEntity<Response<AggregationResults<Object>>> totSingolaProvincia (@RequestParam String sigla) {		
		AggregationResults<Object> body = aggregator.sommaFiltra("dose1", sigla);
		
		Response<AggregationResults<Object>> res = successResponse(
				"Totale singola dose " + sigla , body);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	// Restituire il numero totale della regione lombardia dei vaccinati con doppia dose per provincia
	@GetMapping(path = "tot_doppia/prov")
	public ResponseEntity<Response<AggregationResults<Object>>> totDoppiaProvincia (@RequestParam String sigla) {
		AggregationResults<Object> body = aggregator.sommaFiltra("dose2", sigla);
		
		Response<AggregationResults<Object>> res = successResponse(
				"Totale doppia dose " + sigla, body);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	// Lista ordinata per numero di vaccinati in doppia dose di tutta la lombardia
	@GetMapping(path = "lista_doppia")
	public ResponseEntity<Response<AggregationResults<Object>>> listaDoppia () {
		AggregationResults<Object> body = aggregator.listaDoppia();
		
		Response<AggregationResults<Object>> res = successResponse(
				"Vaccinati doppia dose", body);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	// Lista ordinata per numero di vaccinati in doppia dose di una data provincia
	@GetMapping(path = "lista_doppia/prov")
	public ResponseEntity<Response<AggregationResults<Object>>> listaDoppiaProvincia (@RequestParam String sigla) {
		AggregationResults<Object> body = aggregator.listaDoppiaFiltra(sigla);
		
		Response<AggregationResults<Object>> res = successResponse(
				"Vaccinati doppia dose " + sigla, body);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	// Trovare il comune con più vaccinati in singola dose
	@GetMapping(path = "comune_max_singola")
	public ResponseEntity<Response<AggregationResults<Object>>> comuneMaxSingola () {
		AggregationResults<Object> body = aggregator.capoSomma("dose1", Capo.max);
		
		Response<AggregationResults<Object>> res = successResponse(
				"Comune con più vaccinati una dose", body);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	// Trovare il comune con più vaccinati in doppia dose
	@GetMapping(path = "comune_max_doppia")
	public ResponseEntity<Response<AggregationResults<Object>>> comuneMaxDoppia () {
		AggregationResults<Object> body = aggregator.capoSomma("dose2", Capo.max);
		
		Response<AggregationResults<Object>> res = successResponse(
				"Comune con più vaccinati doppia dose", body);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	// Trovare il comune con meno vaccinati in singola dose
	@GetMapping(path = "comune_min_singola")
	public ResponseEntity<Response<AggregationResults<Object>>> comuneMinSingola () {
		AggregationResults<Object> body = aggregator.capoSomma("dose1", Capo.min);
		
		Response<AggregationResults<Object>> res = successResponse(
				"Comune con meno vaccinati singola dose", body);
		return new ResponseEntity<>(res, HttpStatus.OK);	
	}
	
	// Trovare il comune con meno vaccinati in doppia dose
	@GetMapping(path = "comune_min_doppia")
	public ResponseEntity<Response<AggregationResults<Object>>> comuneMinDoppia () {
		AggregationResults<Object> body = aggregator.capoSomma("dose2", Capo.min);
		
		Response<AggregationResults<Object>> res = successResponse(
				"Comune con più vaccinati doppia dose", body);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	// Trovare il comune con più vaccinati in singola dose per una data provincia
	@GetMapping(path = "comune_max_singola/prov")
	public ResponseEntity<Response<AggregationResults<Object>>> comuneMaxSingola (@RequestParam String sigla) {
		AggregationResults<Object> body = aggregator.capoSommaFiltra("dose1", Capo.max, sigla);
		
		Response<AggregationResults<Object>> res = successResponse(
				"Comune in provincia " + sigla + "con più vaccinati una dose", body);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	// Trovare il comune con più vaccinati in doppia dose per una data provincia
	@GetMapping(path = "comune_max_doppia/prov")
	public ResponseEntity<Response<AggregationResults<Object>>> comuneMaxDoppia (@RequestParam String sigla) {
		AggregationResults<Object> body = aggregator.capoSommaFiltra("dose2", Capo.max, sigla);
		
		Response<AggregationResults<Object>> res = successResponse(
				"Comune in provincia " + sigla + "con più vaccinati doppia dose", body);
		return new ResponseEntity<>(res, HttpStatus.OK);
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
