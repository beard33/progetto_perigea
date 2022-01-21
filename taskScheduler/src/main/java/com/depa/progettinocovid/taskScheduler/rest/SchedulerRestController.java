package com.depa.progettinocovid.taskScheduler.rest;

import java.util.Date;
import java.util.List;

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
	public ResponseEntity<ScheduledInfo> schedule(@PathVariable String tema, 
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date data) {
		
		return new ResponseEntity<>(service.scheduleEstrazione(tema, data), HttpStatus.OK);
	}
	
	@GetMapping(path = "schedule_periodico/{tema}")
	public ResponseEntity<ScheduledInfo> schedulePeriodico(@PathVariable String tema,
			@RequestParam String cron) {
		
		// FIXME NextFireTime = null
		return new ResponseEntity<>(service.scheduleEstrazioneCron(tema, cron), HttpStatus.OK);
	}
	
	@GetMapping(path = "delete/{tema}")
	public ResponseEntity<String> delete(@PathVariable String tema, @RequestParam String id) throws SchedulerException{
		
		return new ResponseEntity<>(service.deleteEstrazione(id, tema) ? "Deleted" : "Not found", HttpStatus.OK);
	}
	
	@GetMapping(path = "reschedule/{tema}", params = {"id", "data"})
	public ResponseEntity<ScheduledInfo> reschedule(@PathVariable String tema, 
			@RequestParam String id, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date data) throws SchedulerException {
		
		return new ResponseEntity<>(service.reschedule(data, id, tema), HttpStatus.OK);
	}
	
	@GetMapping(path = "reschedule/{tema}", params = {"id", "cron"})
	public ResponseEntity<ScheduledInfo> reschedule(@PathVariable String tema, 
			@RequestParam String id, @RequestParam String cron) throws SchedulerException {
		
		return new ResponseEntity<>(service.reschedule(cron, id, tema),	HttpStatus.OK);
	}
	
	@GetMapping(path = "tutti/")
	public ResponseEntity<List<ScheduledInfo>> tutti(@RequestParam String tema){
		List<ScheduledInfo> tutti = service.getAll();
		
		return new ResponseEntity<>(tutti, HttpStatus.OK);
	}
}