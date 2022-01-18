package com.depa.progettinocovid.DataExtractor.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.depa.progettinocovid.DataExtractor.factory.ServiceFactory;
import com.depa.progettinocovid.DataExtractor.service.GenericService;

@RestController
public class EstrazioneRestController {
	
	@Autowired
	ServiceFactory factory;
	
	@GetMapping(path = "/estrai/{tema}")
	public ResponseEntity<Response<Object>> estrai (@PathVariable String tema){
		GenericService service = factory.getService(tema);
		service.prendiDati();
		service.pubblicaDati();
		
		return null;
	}
}