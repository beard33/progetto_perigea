package com.depa.progettinocovid.taskScheduler.service;

import java.util.Date;
import java.util.UUID;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.depa.progettinocovid.taskScheduler.jobs.SomministrazioniJob;
import com.depa.progettinocovid.taskScheduler.jobs.StatiCliniciJob;

@Service
public class SchedulerService {
	
	@Autowired
	private Scheduler scheduler;

	// TODO factory
	public String scheduleRichiesta(String tema, Date dataEsecuzione) {	
		JobDetail detail = buildJobDetail(tema);
		Trigger trigger = buildJobTrigger(detail, dataEsecuzione);
		try {
			scheduler.scheduleJob(detail, trigger);
			System.out.println("Next run: " + trigger.getNextFireTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return detail.getKey().getName();
	}
	
    private JobDetail buildJobDetail(String tema) {
    	
        return JobBuilder.newJob(tema == "somministrazioni" ? SomministrazioniJob.class : StatiCliniciJob.class)
                .withIdentity(UUID.randomUUID().toString(), tema)
                .withDescription("Job scheduler for " + tema)
                .build();
    }
	
	private Trigger buildJobTrigger(JobDetail detail, Date dataEsecuzione) {
		
		return TriggerBuilder.newTrigger()
				.forJob(detail)
				.withIdentity(detail.getKey().getName(), detail.getKey().getGroup())
				.withDescription(detail.getDescription())
				.startAt(dataEsecuzione)
				.build();
		
	}
	
}
