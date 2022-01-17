package com.depa.progettinocovid.rest;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.depa.progettinocovid.exceptions.ProvinciaNotFoundException;
import com.depa.progettinocovid.repository.ProvinciaRepository;
import com.depa.progettinocovid.service.ConteggioAggregationService;
import com.depa.progettinocovid.service.ConteggioAggregationService.Capo;

/** espone vari endpoint che servono dati aggregati sulle somministrazioni avvenute in Lombardia
 * */
@RestController
//@RequestMapping("/dosi")
public class VacciniRestController {
	
	@Autowired
	private ConteggioAggregationService aggregator;
	
	// TODO - Svincolare Data aggregator da postgres
	@Autowired
	private ProvinciaRepository provinceRepo;
	
	// Restituire il numero totale della regione lombardia dei vaccinati con singola dose
	@GetMapping(path = "tot_singola")
	public ResponseEntity<Response<Document>> totSingola () {
		Document body = aggregator.somma("dose1");
		
		Response<Document> res = successResponse("Totale una dose", body);
		return new ResponseEntity<Response<Document>>(res, HttpStatus.OK);
	}
	
	// Restituire il numero totale della regione lombardia dei vaccinati con doppia dose 
	@GetMapping(path = "tot_doppia")
	public ResponseEntity<Response<Document>> totDoppia () {
		Document body = aggregator.somma("dose2");
		
		Response<Document> res = successResponse("Totale due dosi", body);
		return new ResponseEntity<Response<Document>>(res, HttpStatus.OK);
	}
	
	// Restituire il numero totale della regione lombardia dei vaccinati con singola dose per provincia
	@GetMapping(path = "tot_singola/prov")
	public ResponseEntity<Response<Document>> totSingolaProvincia (@RequestParam String sigla) {		
		if (provinceRepo.findBySigla(sigla) == null)
			throw new ProvinciaNotFoundException(sigla);
		
		Document body = aggregator.sommaFiltra("dose1", sigla);
		Response<Document> res = successResponse("Totale singola dose " + sigla , body);
		return new ResponseEntity<Response<Document>>(res, HttpStatus.OK);
	}
	
	// Restituire il numero totale della regione lombardia dei vaccinati con doppia dose per provincia
	@GetMapping(path = "tot_doppia/prov")
	public ResponseEntity<Response<Document>> totDoppiaProvincia (@RequestParam String sigla) {
		if (provinceRepo.findBySigla(sigla) == null)
			throw new ProvinciaNotFoundException(sigla);
		
		Document body = aggregator.sommaFiltra("dose2", sigla);
		Response<Document> res = successResponse("Totale doppia dose " + sigla, body);
		return new ResponseEntity<Response<Document>>(res, HttpStatus.OK);
	}
	
	// Lista ordinata per numero di vaccinati in doppia dose di tutta la lombardia
	@GetMapping(path = "lista_doppia")
	public ResponseEntity<Response<Document>> listaDoppia () {
		Document body = aggregator.listaDoppia();
		
		Response<Document> res = successResponse("Vaccinati doppia dose", body);
		return new ResponseEntity<Response<Document>>(res, HttpStatus.OK);
	}
	
	// Lista ordinata per numero di vaccinati in doppia dose di una data provincia
	@GetMapping(path = "lista_doppia/prov")
	public ResponseEntity<Response<Document>> listaDoppiaProvincia (@RequestParam String sigla) {
		if (provinceRepo.findBySigla(sigla) == null)
			throw new ProvinciaNotFoundException(sigla);
		
		Document body = aggregator.listaDoppiaFiltra(sigla);
		Response<Document> res = successResponse("Vaccinati doppia dose " + sigla, body);
		return new ResponseEntity<Response<Document>>(res, HttpStatus.OK);
	}
	
	// Trovare il comune con più vaccinati in singola dose
	@GetMapping(path = "comune_max_singola")
	public ResponseEntity<Response<Document>> comuneMaxSingola () {
		Document body = aggregator.capoSomma("dose1", Capo.max);
		
		Response<Document> res = successResponse("Comune con più vaccinati una dose", body);
		return new ResponseEntity<Response<Document>>(res, HttpStatus.OK);
	}
	
	// Trovare il comune con più vaccinati in doppia dose
	@GetMapping(path = "comune_max_doppia")
	public ResponseEntity<Response<Document>> comuneMaxDoppia () {
		Document body = aggregator.capoSomma("dose2", Capo.max);
		
		Response<Document> res = successResponse("Comune con più vaccinati doppia dose", body);
		return new ResponseEntity<Response<Document>>(res, HttpStatus.OK);
	}
	
	// Trovare il comune con meno vaccinati in singola dose
	@GetMapping(path = "comune_min_singola")
	public ResponseEntity<Response<Document>> comuneMinSingola () {
		Document body = aggregator.capoSomma("dose1", Capo.min);
		
		Response<Document> res = successResponse("Comune con meno vaccinati singola dose", body);
		return new ResponseEntity<Response<Document>>(res, HttpStatus.OK);	
	}
	
	// Trovare il comune con meno vaccinati in doppia dose
	@GetMapping(path = "comune_min_doppia")
	public ResponseEntity<Response<Document>> comuneMinDoppia () {
		Document body = aggregator.capoSomma("dose2", Capo.min);
		
		Response<Document> res = successResponse("Comune con più vaccinati doppia dose", body);
		return new ResponseEntity<Response<Document>>(res, HttpStatus.OK);
	}
	
	// Trovare il comune con più vaccinati in singola dose per una data provincia
	@GetMapping(path = "comune_max_singola/prov")
	public ResponseEntity<Response<Document>> comuneMaxSingola (@RequestParam String sigla) {
		if (provinceRepo.findBySigla(sigla) == null)
			throw new ProvinciaNotFoundException(sigla);
		
		Document body = aggregator.capoSommaFiltra("dose1", Capo.max, sigla);
		Response<Document> res = successResponse("Comune in provincia " + sigla + " con più vaccinati una dose", body);
		return new ResponseEntity<Response<Document>>(res, HttpStatus.OK);
	}
	
	// Trovare il comune con più vaccinati in doppia dose per una data provincia
	@GetMapping(path = "comune_max_doppia/prov")
	public ResponseEntity<Response<Document>> comuneMaxDoppia (@RequestParam String sigla) {
		if (provinceRepo.findBySigla(sigla) == null)
			throw new ProvinciaNotFoundException(sigla);
		
		Document body = aggregator.capoSommaFiltra("dose2", Capo.max, sigla);
		Response<Document> res = successResponse("Comune in provincia " + sigla + " con più vaccinati doppia dose", body);
		return new ResponseEntity<Response<Document>>(res, HttpStatus.OK);
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
