package com.depa.progettinocovid.taskScheduler.rest;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.depa.progettinocovid.taskScheduler.service.SchedulerService;

@RestController
public class SchedulerRestController {
	
	@Autowired
	private SchedulerService service;
	
	// intervallo numero di giorni? ore? boh
	@GetMapping(path = "schedule/{tema}")
	public ResponseEntity<String> schedule(@PathVariable String tema, 
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataEsecuzione) {
		
		String id = service.scheduleRichiesta(tema, dataEsecuzione);
		
		return new ResponseEntity<>(id, HttpStatus.OK);
	}
	
	//@GetMapping(path = "delete/{}")
	
}