package com.depa.progettinocovid.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.depa.progettinocovid.repository.ConteggioRepositoryCustom;
import com.depa.progettinocovid.repository.ConteggioRepositoryCustom.Capo;

@RestController
public class VacciniRestController {
	
	@Autowired
	ConteggioRepositoryCustom repository;
	
	// Restituire il numero totale della regione lombardia dei vaccinati con singola dose
	@GetMapping(path = "tot_singola")
	public ResponseEntity<AggregationResults<Object>> totSingola () {
		return new ResponseEntity<AggregationResults<Object>>(repository.somma("dose1"), HttpStatus.OK);
	}
	
	// Restituire il numero totale della regione lombardia dei vaccinati con doppia dose 
	@GetMapping(path = "tot_doppia")
	public ResponseEntity<AggregationResults<Object>> totDoppia () {
		return new ResponseEntity<AggregationResults<Object>>(repository.somma("dose2"), HttpStatus.OK);
	}
	
	// Restituire il numero totale della regione lombardia dei vaccinati con singola dose per provincia
	@GetMapping(path = "tot_singola/prov")
	public ResponseEntity<AggregationResults<Object>> totSingolaProvincia (@RequestParam String sigla) {		
		return new ResponseEntity<AggregationResults<Object>>(repository.sommaFiltra("dose1", sigla), HttpStatus.OK);
	}
	
	// Restituire il numero totale della regione lombardia dei vaccinati con doppia dose per provincia
	@GetMapping(path = "tot_doppia/prov")
	public ResponseEntity<AggregationResults<Object>> totDoppiaProvincia (@RequestParam String sigla) {
		return new ResponseEntity<AggregationResults<Object>>(repository.sommaFiltra("dose1", sigla), HttpStatus.OK);
	}
	
	// Lista ordinata per numero di vaccinati in doppia dose di tutta la lombardia
	@GetMapping(path = "lista_doppia")
	public ResponseEntity<AggregationResults<Object>> listaDoppia () {
		return new ResponseEntity<AggregationResults<Object>>(repository.listaDoppia(), HttpStatus.OK);
	}
	
	// Lista ordinata per numero di vaccinati in doppia dose di una data provincia
	@GetMapping(path = "lista_doppia/prov")
	public ResponseEntity<AggregationResults<Object>> listaDoppiaProvincia (@RequestParam String sigla) {
		return new ResponseEntity<AggregationResults<Object>>(repository.listaDoppiaFiltra(sigla), HttpStatus.OK);
	}
	
	// Trovare il comune con più vaccinati in singola dose
	@GetMapping(path = "comune_max_singola")
	public ResponseEntity<AggregationResults<Object>> comuneMaxSingola () {
		return new ResponseEntity<AggregationResults<Object>>(repository.capoSomma("dose1", Capo.max), HttpStatus.OK);
	}
	
	// Trovare il comune con più vaccinati in doppia dose
	@GetMapping(path = "comune_max_doppia")
	public ResponseEntity<AggregationResults<Object>> comuneMaxDoppia () {
		return new ResponseEntity<AggregationResults<Object>>(repository.capoSomma("dose2", Capo.max), HttpStatus.OK);
	}
	
	// Trovare il comune con meno vaccinati in singola dose
	@GetMapping(path = "comune_min_singola")
	public ResponseEntity<AggregationResults<Object>> comuneMinSingola () {
		return new ResponseEntity<AggregationResults<Object>>(repository.capoSomma("dose1", Capo.min), HttpStatus.OK);
	}
	
	// Trovare il comune con meno vaccinati in doppia dose
	@GetMapping(path = "comune_min_doppia")
	public ResponseEntity<AggregationResults<Object>> comuneMinDoppia () {
		return new ResponseEntity<AggregationResults<Object>>(repository.capoSomma("dose2", Capo.min), HttpStatus.OK);
	}
	
	// Trovare il comune con più vaccinati in singola dose per una data provincia
	@GetMapping(path = "comune_max_singola/prov")
	public ResponseEntity<AggregationResults<Object>> comuneMaxSingola (@RequestParam String sigla) {
		return new ResponseEntity<AggregationResults<Object>>(repository.capoSommaFiltra("dose1", Capo.max, sigla), HttpStatus.OK);
	}
	
	// Trovare il comune con più vaccinati in doppia dose per una data provincia
	@GetMapping(path = "comune_max_doppia/prov")
	public ResponseEntity<AggregationResults<Object>> comuneMaxDoppia (@RequestParam String sigla) {
		return new ResponseEntity<AggregationResults<Object>>(repository.capoSommaFiltra("dose2", Capo.max, sigla), HttpStatus.OK);
	}
}
