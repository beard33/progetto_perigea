package com.depa.progettinocovid.taskScheduler.rest;

import org.quartz.CronTrigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.scheduling.quartz.CronTriggerFactoryBean;

import com.depa.progettinocovid.taskScheduler.service.SchedulerService;

@RestController
public class SchedulerRestController {
	
	@Autowired
	private SchedulerService service;
	
	// intervallo numero di giorni? ore? boh
	@GetMapping(path = "schedule/{tema}")
	public ResponseEntity<String> schedule(@PathVariable String tema, @RequestParam String cron) {
		
		CronTriggerFactoryBean triggerFactoryBean = new CronTriggerFactoryBean();
		triggerFactoryBean.setCronExpression(cron);
		CronTrigger trigger = triggerFactoryBean.getObject();
		service.scheduleRichiesta(tema, trigger);
		
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}
	
	//@GetMapping(path = "delete/{}")
	
}