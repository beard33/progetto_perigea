package com.depa.progettinocovid.DataExtractor.rest;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.depa.progettinocovid.DataExtractor.factory.ServiceFactory;
import com.depa.progettinocovid.DataExtractor.model.Processo;
import com.depa.progettinocovid.DataExtractor.service.GenericService;
import com.depa.progettinocovid.DataExtractor.service.ProcessoService;

@RestController
public class EstrazioneRestController {
	
	@Autowired
	ServiceFactory factory;
	
	@Autowired
	ProcessoService processoService;
	
	@GetMapping(path = "/estrai/{tema}")
	public ResponseEntity<Response<Object>> estrai (@PathVariable String tema){
		Processo processo = new Processo();
		processo.setTipo(tema);
//		TODO controllare consistenza del tema
		processo.setInizio(new Date());
		GenericService service = factory.getService(tema);
		service.prendiDati();
		service.pubblicaDati();
		processo.setFine(new Date());
		
		processoService.save(processo);
//		TODO fare response
		return null;
	}
}