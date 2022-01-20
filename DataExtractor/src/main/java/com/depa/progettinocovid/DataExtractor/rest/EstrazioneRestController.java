package com.depa.progettinocovid.DataExtractor.rest;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.depa.progettinocovid.DataExtractor.exceptions.BadTemaRequestException;
import com.depa.progettinocovid.DataExtractor.factory.ServiceFactory;
import com.depa.progettinocovid.DataExtractor.model.Processo;
import com.depa.progettinocovid.DataExtractor.service.GenericService;
import com.depa.progettinocovid.DataExtractor.service.ProcessoService;

import commons.model.TemaEnum;
import commons.rest.Response;

@RestController
public class EstrazioneRestController {
	
	@Autowired
	private ServiceFactory factory;
	
	@Autowired
	private ProcessoService processoService;
	
	// TODO ENUM
	@GetMapping(path = "/estrai/{tema}")
	public ResponseEntity<Response<Object>> estrai (@PathVariable String tema){
		
		TemaEnum temaPassato;
		try {
			temaPassato = TemaEnum.valueOf(tema.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new BadTemaRequestException();
		}
		
		Processo processo = new Processo();
		processo.setTipo(tema);
		processo.setInizio(new Date());
		GenericService service = factory.getService(temaPassato);
		service.prendiDati();
		service.pubblicaDati();
		processo.setFine(new Date());
		
		processoService.save(processo);
		
		Response<Object> res = Response.<Object>builder()
				.type(Response.Type.SUCCESS)
				.code(HttpStatus.OK.value())
				.description("Request sent")
				.build();
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
}