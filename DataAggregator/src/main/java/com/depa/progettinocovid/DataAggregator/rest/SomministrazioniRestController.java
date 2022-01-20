package com.depa.progettinocovid.DataAggregator.rest;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.depa.progettinocovid.DataAggregator.exceptions.ProvinciaNotFoundException;
import com.depa.progettinocovid.DataAggregator.model.AggregationResult;
import com.depa.progettinocovid.DataAggregator.service.SomministrazioneAggregationService;
import com.depa.progettinocovid.DataAggregator.service.SomministrazioneAggregationService.Capo;
import com.depa.progettinocovid.DataAggregator.service.SomministrazioneService;

import commons.rest.Response;

/** espone vari endpoint che servono dati aggregati sulle somministrazioni avvenute in Lombardia
 * */
@RestController
//@RequestMapping("/dosi")
public class SomministrazioniRestController {
	
	@Autowired
	private SomministrazioneAggregationService aggregator;
	
	@Autowired
	private SomministrazioneService service;
	
	@Autowired
	private ExecutorService executorService;
	
	// Restituire il numero totale della regione lombardia dei vaccinati con singola dose
	@GetMapping(path = "tot_singola")
	public ResponseEntity<Response<AggregationResult>> totSingola () throws Exception {
		
		Callable<ResponseEntity<Response<AggregationResult>>> callable = () -> {
			Response<AggregationResult> res = successResponse("Totale una dose", aggregator.somma("dose1"));
			return new ResponseEntity<Response<AggregationResult>>(res, HttpStatus.OK);
		};
		Future<ResponseEntity<Response<AggregationResult>>> future = executorService.submit(callable);
		
		ResponseEntity<Response<AggregationResult>> res = future.get(200, TimeUnit.MILLISECONDS);
		
		return res;
	}
	
	// Restituire il numero totale della regione lombardia dei vaccinati con doppia dose 
	@GetMapping(path = "tot_doppia")
	public ResponseEntity<Response<AggregationResult>> totDoppia () throws Exception {
		
		Callable<ResponseEntity<Response<AggregationResult>>> callable = () -> {
			Response<AggregationResult> res = successResponse("Totale due dosi", aggregator.somma("dose2"));
			return new ResponseEntity<Response<AggregationResult>>(res, HttpStatus.OK);
		};
		Future<ResponseEntity<Response<AggregationResult>>> future = executorService.submit(callable);
		
		ResponseEntity<Response<AggregationResult>> res = future.get(200, TimeUnit.MILLISECONDS);
		
		return res;
	}
	
	// Restituire il numero totale della regione lombardia dei vaccinati con singola dose per provincia
	@GetMapping(path = "tot_singola/prov")
	public ResponseEntity<Response<AggregationResult>> totSingolaProvincia (@RequestParam String sigla) throws Exception {
		
		Callable<ResponseEntity<Response<AggregationResult>>> callable = () -> {
			if (!service.siglaEsiste(sigla))
				throw new ProvinciaNotFoundException(sigla);
			
			Response<AggregationResult> res = successResponse("Totale singola dose " + sigla , aggregator.sommaFiltra("dose1", sigla));
			return new ResponseEntity<Response<AggregationResult>>(res, HttpStatus.OK);
		};
		Future<ResponseEntity<Response<AggregationResult>>> future = executorService.submit(callable);
		
		ResponseEntity<Response<AggregationResult>> res = future.get(200, TimeUnit.MILLISECONDS);
		
		return res;
	}
	
	// Restituire il numero totale della regione lombardia dei vaccinati con doppia dose per provincia
	@GetMapping(path = "tot_doppia/prov")
	public ResponseEntity<Response<AggregationResult>> totDoppiaProvincia (@RequestParam String sigla) throws Exception {
		
		Callable<ResponseEntity<Response<AggregationResult>>> callable = () -> {
			if (!service.siglaEsiste(sigla))
				throw new ProvinciaNotFoundException(sigla);
			
			Response<AggregationResult> res = successResponse("Totale doppia dose " + sigla, aggregator.sommaFiltra("dose2", sigla));
			return new ResponseEntity<Response<AggregationResult>>(res, HttpStatus.OK);
		};
		Future<ResponseEntity<Response<AggregationResult>>> future = executorService.submit(callable);
		
		ResponseEntity<Response<AggregationResult>> res = future.get(200, TimeUnit.MILLISECONDS);
		
		return res;
	}
	
	// Lista ordinata per numero di vaccinati in doppia dose di tutta la lombardia
	@GetMapping(path = "lista_doppia")
	public ResponseEntity<Response<Document>> listaDoppia () throws Exception {
		
		Callable<ResponseEntity<Response<Document>>> callable = () -> {
			Document body = aggregator.listaDoppia();
			
			Response<Document> res = successResponse("Vaccinati doppia dose", body);
			return new ResponseEntity<Response<Document>>(res, HttpStatus.OK);
		};
		Future<ResponseEntity<Response<Document>>> future = executorService.submit(callable);
		
		ResponseEntity<Response<Document>> res = future.get(200, TimeUnit.MILLISECONDS);
		
		return res;
	}
	
	// Lista ordinata per numero di vaccinati in doppia dose di una data provincia
	@GetMapping(path = "lista_doppia/prov")
	public ResponseEntity<Response<Document>> listaDoppiaProvincia (@RequestParam String sigla) throws Exception {
		
		Callable<ResponseEntity<Response<Document>>> callable = () -> {
			if (!service.siglaEsiste(sigla))
				throw new ProvinciaNotFoundException(sigla);
			
			Document body = aggregator.listaDoppiaFiltra(sigla);
			Response<Document> res = successResponse("Vaccinati doppia dose " + sigla, body);
			return new ResponseEntity<Response<Document>>(res, HttpStatus.OK);
		};
		Future<ResponseEntity<Response<Document>>> future = executorService.submit(callable);
		
		ResponseEntity<Response<Document>> res = future.get(200, TimeUnit.MILLISECONDS);
		
		return res;
	}
	
	// Trovare il comune con più vaccinati in singola dose
	@GetMapping(path = "comune_max_singola")
	public ResponseEntity<Response<AggregationResult>> comuneMaxSingola () throws Exception {
		
		Callable<ResponseEntity<Response<AggregationResult>>> callable = () -> {
			
			Response<AggregationResult> res = successResponse("Comune con più vaccinati una dose", aggregator.capoSomma("dose1", Capo.max));
			return new ResponseEntity<Response<AggregationResult>>(res, HttpStatus.OK);
		};
		Future<ResponseEntity<Response<AggregationResult>>> future = executorService.submit(callable);
		
		ResponseEntity<Response<AggregationResult>> res = future.get(200, TimeUnit.MILLISECONDS);
		
		return res;
	}
	
	// Trovare il comune con più vaccinati in doppia dose
	@GetMapping(path = "comune_max_doppia")
	public ResponseEntity<Response<AggregationResult>> comuneMaxDoppia () throws Exception {
		
		Callable<ResponseEntity<Response<AggregationResult>>> callable = () -> {
			
			Response<AggregationResult> res = successResponse("Comune con più vaccinati doppia dose", aggregator.capoSomma("dose2", Capo.max));
			return new ResponseEntity<Response<AggregationResult>>(res, HttpStatus.OK);
		};
		Future<ResponseEntity<Response<AggregationResult>>> future = executorService.submit(callable);
		
		ResponseEntity<Response<AggregationResult>> res = future.get(200, TimeUnit.MILLISECONDS);
		
		return res;
	}
	
	// Trovare il comune con meno vaccinati in singola dose
	@GetMapping(path = "comune_min_singola")
	public ResponseEntity<Response<AggregationResult>> comuneMinSingola () throws Exception {
		
		Callable<ResponseEntity<Response<AggregationResult>>> callable = () -> {
			
			Response<AggregationResult> res = successResponse("Comune con meno vaccinati singola dose", aggregator.capoSomma("dose1", Capo.min));
			return new ResponseEntity<Response<AggregationResult>>(res, HttpStatus.OK);
		};
		Future<ResponseEntity<Response<AggregationResult>>> future = executorService.submit(callable);
		
		ResponseEntity<Response<AggregationResult>> res = future.get(200, TimeUnit.MILLISECONDS);
		
		return res;
	}
	
	// Trovare il comune con meno vaccinati in doppia dose
	@GetMapping(path = "comune_min_doppia")
	public ResponseEntity<Response<AggregationResult>> comuneMinDoppia () throws Exception {
		
		Callable<ResponseEntity<Response<AggregationResult>>> callable = () -> {
			
			Response<AggregationResult> res = successResponse("Comune con più vaccinati doppia dose", aggregator.capoSomma("dose2", Capo.min));
			return new ResponseEntity<Response<AggregationResult>>(res, HttpStatus.OK);
		};
		Future<ResponseEntity<Response<AggregationResult>>> future = executorService.submit(callable);
		
		ResponseEntity<Response<AggregationResult>> res = future.get(200, TimeUnit.MILLISECONDS);
		
		return res;
	}
	
	// Trovare il comune con più vaccinati in singola dose per una data provincia
	@GetMapping(path = "comune_max_singola/prov")
	public ResponseEntity<Response<AggregationResult>> comuneMaxSingola (@RequestParam String sigla) throws Exception {
		
		Callable<ResponseEntity<Response<AggregationResult>>> callable = () -> {
			if (!service.siglaEsiste(sigla))
				throw new ProvinciaNotFoundException(sigla);
			
			Response<AggregationResult> res = successResponse(
					"Comune in provincia " + sigla + " con più vaccinati una dose",
					aggregator.capoSommaFiltra("dose1", Capo.max, sigla));
			return new ResponseEntity<Response<AggregationResult>>(res, HttpStatus.OK);
		};
		Future<ResponseEntity<Response<AggregationResult>>> future = executorService.submit(callable);
		
		ResponseEntity<Response<AggregationResult>> res = future.get(200, TimeUnit.MILLISECONDS);
		
		return res;
	}
	
	// Trovare il comune con più vaccinati in doppia dose per una data provincia
	@GetMapping(path = "comune_max_doppia/prov")
	public ResponseEntity<Response<AggregationResult>> comuneMaxDoppia (@RequestParam String sigla) throws Exception {
		
		Callable<ResponseEntity<Response<AggregationResult>>> callable = () -> {
			if (!service.siglaEsiste(sigla))
				throw new ProvinciaNotFoundException(sigla);
			
			Response<AggregationResult> res = successResponse(
					"Comune in provincia " + sigla + " con più vaccinati doppia dose",
					aggregator.capoSommaFiltra("dose2", Capo.max, sigla));
			return new ResponseEntity<Response<AggregationResult>>(res, HttpStatus.OK);
		};
		Future<ResponseEntity<Response<AggregationResult>>> future = executorService.submit(callable);
		
		ResponseEntity<Response<AggregationResult>> res = future.get(200, TimeUnit.MILLISECONDS);
		
		return res;
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
