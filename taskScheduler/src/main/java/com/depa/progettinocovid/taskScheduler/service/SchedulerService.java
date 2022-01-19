package com.depa.progettinocovid.taskScheduler.service;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchedulerService {

	@Autowired
	private JobDetail somministrazioniJobDetail;
	
	@Autowired 
	private JobDetail statiCliniciJobDetail;
	
	@Autowired
	private Scheduler scheduler;

	// TODO factory
	public void scheduleRichiesta(String tema, CronTrigger trigger) {
		try {
			if (!scheduler.isStarted()) {
				scheduler.start();
			}
			if(tema.equalsIgnoreCase("somministrazioni")) {
				scheduler.scheduleJob(somministrazioniJobDetail, trigger);
				System.out.println("Job scheduled for " + trigger.getNextFireTime().toString());
			}
			if(tema.equalsIgnoreCase("stati-clinici")) {
				scheduler.scheduleJob(statiCliniciJobDetail, trigger);
				System.out.println("Job scheduled for " + trigger.getNextFireTime().toString());
			}
			
			
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
}
