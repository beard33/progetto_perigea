package com.depa.progettinocovid.taskScheduler.service;

import java.util.Date;
import java.util.UUID;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.depa.progettinocovid.taskScheduler.jobs.SomministrazioniJob;
import com.depa.progettinocovid.taskScheduler.jobs.StatiCliniciJob;

@Service
public class SchedulerService {
	
	@Autowired
	private Scheduler scheduler;

	// TODO factory
	public String scheduleEstrazione(String tema, Date dataEsecuzione) {	
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
	
	public boolean deleteEstrazione(String id, String tema) throws SchedulerException {
		JobKey key = new JobKey(id, tema);
		return scheduler.deleteJob(key);
	}
	
	public Date reschedule(Date nuovaData, String id, String tema) throws SchedulerException {
		JobKey jobKey = new JobKey(id, tema);
		if (!scheduler.checkExists(jobKey)) {
			return null;
		}
		TriggerKey triggerKey = new TriggerKey(id, tema);
		JobDetail detail = scheduler.getJobDetail(jobKey);
		Trigger trigger = buildJobTrigger(detail, nuovaData, id);
		return scheduler.rescheduleJob(triggerKey, trigger);
	}
	
    private JobDetail buildJobDetail(String tema) {
    	
        return JobBuilder.newJob(tema == "somministrazioni" ? SomministrazioniJob.class : StatiCliniciJob.class)
                .withIdentity(UUID.randomUUID().toString(), tema)
                .withDescription("Job scheduler for " + tema)
                .build();
    }
    
    private Trigger buildJobTrigger(JobDetail detail, Date dataEsecuzione, String id) {
    	
    	return TriggerBuilder.newTrigger()
				.forJob(detail)
				.withIdentity(id, detail.getKey().getGroup())
				.withDescription(detail.getDescription())
				.startAt(dataEsecuzione)
				.build();
    }
	
	private Trigger buildJobTrigger(JobDetail detail, Date dataEsecuzione) {
		
		return buildJobTrigger(detail, dataEsecuzione, detail.getKey().getName());
		
	}
	
}
