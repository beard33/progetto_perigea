package com.depa.progettinocovid.taskScheduler.rest;

import java.util.Date;

import org.quartz.SchedulerException;
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
	public ResponseEntity<SchedulerResponse> schedule(@PathVariable String tema, 
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date data) {
		
		String id = service.scheduleEstrazione(tema, data);
		
		return new ResponseEntity<>(new SchedulerResponse(id, data, tema), 
				HttpStatus.OK);
	}
	
	@GetMapping(path = "delete/{tema}")
	public ResponseEntity<String> delete(@PathVariable String tema, @RequestParam String id) throws SchedulerException{
		return new ResponseEntity<>(service.deleteEstrazione(id, tema) ? "Deleted" : "Not found", HttpStatus.OK);
	}
	
	@GetMapping(path = "reschedule/{tema}")
	public ResponseEntity<SchedulerResponse> reschedule(@PathVariable String tema, 
			@RequestParam String id, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date data) throws SchedulerException {
		Date nuovaData = service.reschedule(data, id, tema);
		if (nuovaData == null) {
			return new ResponseEntity<>(new SchedulerResponse(null, null, null),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new SchedulerResponse(id, nuovaData, tema),
				HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}