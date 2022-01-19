package com.depa.progettinocovid.taskScheduler.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SchedulerRestController {
	
	// intervallo numero di giorni? ore? boh
	@GetMapping(path = "schedule/{tema}")
	public ResponseEntity<String> schedule(@PathVariable String tema, @RequestParam int intervallo) {
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
}
