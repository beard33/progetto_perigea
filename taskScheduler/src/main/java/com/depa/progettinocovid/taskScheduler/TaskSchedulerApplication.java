package com.depa.progettinocovid.taskScheduler;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.depa.progettinocovid.taskScheduler.jobs.SomministrazioniJob;
import com.depa.progettinocovid.taskScheduler.jobs.StatiCliniciJob;

@SpringBootApplication
public class TaskSchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskSchedulerApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	@Bean
	public Scheduler scheduler() {
		try {
			return StdSchedulerFactory.getDefaultScheduler();
		} catch (SchedulerException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	@Bean
	public JobDetail somministrazioniJobDetail() {
		return JobBuilder.newJob().ofType(SomministrazioniJob.class)
				.storeDurably()
			    .withIdentity("Somministrazioni")  
			    .withDescription("Job per le somministrazionis")
			    .build();
	}
	
	@Bean
	public JobDetail statiCliniciJobDetail() {
		return JobBuilder.newJob().ofType(StatiCliniciJob.class)
				.storeDurably()
			    .withIdentity("Stati-clinici")  
			    .withDescription("Job per gli stati-clinici")
			    .build();
	}
	
}
