package com.depa.progettinocovid.taskScheduler.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
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
import com.depa.progettinocovid.taskScheduler.rest.ScheduledInfo;
import com.depa.progettinocovid.taskScheduler.rest.ScheduledInfo.Tipo;

@Service
public class SchedulerService {
	
	@Autowired
	private Scheduler scheduler;
	
	@Autowired
	private ScheduledInfoRepositoryService repositoryService;

	// TODO factory
	public ScheduledInfo scheduleEstrazione(String tema, Date dataEsecuzione) {	
		JobDetail detail = buildJobDetail(tema);
		Trigger trigger = buildJobTrigger(detail, dataEsecuzione);
		try {
			scheduler.scheduleJob(detail, trigger);
		} catch (Exception e) {
			e.printStackTrace();
		}

		ScheduledInfo info = ScheduledInfo.builder()
				.id(detail.getKey().getName())
				.nextFireTime(dataEsecuzione)
				.tema(tema)
				.tipo(Tipo.UNA_TANTUM.name())
				.build();
		repositoryService.save(info);
		return info;
	}
	
	public ScheduledInfo scheduleEstrazioneCron(String tema, String cron) {
		JobDetail detail = buildJobDetail(tema);
		Trigger trigger = buildCronJobTrigger(detail, cron);
		
		try {
			scheduler.scheduleJob(detail, trigger);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ScheduledInfo info = ScheduledInfo.builder()
				.id(detail.getKey().getName())
				.cron(cron)
				.tema(tema)
				.tipo(Tipo.PERIODICO.name())
				.build();
		repositoryService.save(info);
		return info;
	}

	public boolean deleteEstrazione(String id, String tema) throws SchedulerException {
		JobKey key = new JobKey(id, tema);
		repositoryService.deleteJobById(id);
		return scheduler.deleteJob(key);
	}
	
	public ScheduledInfo reschedule(Date nuovaData, String id, String tema) throws SchedulerException {
		JobKey jobKey = new JobKey(id, tema);
		if (!scheduler.checkExists(jobKey)) {
			return null;
		}
		TriggerKey triggerKey = new TriggerKey(id, tema);
		JobDetail detail = buildJobDetail(tema);
		Trigger trigger = buildJobTrigger(detail, nuovaData, id);
		if (scheduler.rescheduleJob(triggerKey, trigger) == null) {
			return new ScheduledInfo();
		}

		ScheduledInfo info = ScheduledInfo.builder()
				.id(detail.getKey().getName())
				.nextFireTime(nuovaData)
				.tema(tema)
				.tipo(Tipo.UNA_TANTUM.name())
				.build();
		repositoryService.save(info);
		return info;
	}

	public ScheduledInfo reschedule(String cron, String id, String tema) throws SchedulerException {
		JobKey jobKey = new JobKey(id, tema);
		if (!scheduler.checkExists(jobKey)) {
			return null;
		}
		TriggerKey triggerKey = new TriggerKey(id, tema);
		JobDetail detail = scheduler.getJobDetail(jobKey);
		Trigger trigger = buildCronJobTrigger(detail, cron, id);
		if (scheduler.rescheduleJob(triggerKey, trigger) == null) {
			return new ScheduledInfo();
		}

		ScheduledInfo info = ScheduledInfo.builder()
				.id(detail.getKey().getName())
				.cron(cron)
				.tema(tema)
				.tipo(Tipo.PERIODICO.name())
				.build();
		repositoryService.save(info);
		return info;
	}

	public void schedule(ScheduledInfo info) throws SchedulerException {
		if (info.getTipo().equals(Tipo.PERIODICO.name())) {
			reschedule(info.getCron(), info.getId(), info.getTema());
		} else {
			reschedule(info.getNextFireTime(), info.getId(), info.getTema());
		}
	}
	
	public void rescheduleAvvio() { 
		List<ScheduledInfo> infos = repositoryService.getAll();
		
		repositoryService.deleteAll();
		
		try {
			for (ScheduledInfo scheduledInfo : infos) {
				JobDetail detail = buildJobDetail(scheduledInfo.getTema());
				Trigger trigger;
				
				if (scheduledInfo.getTipo().equals(Tipo.PERIODICO.name())) {
					trigger = buildCronJobTrigger(detail, scheduledInfo.getCron());
				} else {
					trigger = buildJobTrigger(detail, scheduledInfo.getNextFireTime());
				}
				
				scheduler.scheduleJob(detail, trigger);
				
				scheduledInfo.setId(detail.getKey().getName());
				repositoryService.save(scheduledInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<ScheduledInfo> getAll() {
		return repositoryService.getAll();
	}
	
    private JobDetail buildJobDetail(String tema) {
        return JobBuilder.newJob(tema == "somministrazioni" ? SomministrazioniJob.class : StatiCliniciJob.class)
                .withIdentity(UUID.randomUUID().toString(), tema)
                .withDescription("Job scheduler for " + tema)
                .build();
    }
    
    private Trigger buildJobTrigger(JobDetail detail, Date dataEsecuzione) {
    	return buildJobTrigger(detail, dataEsecuzione, detail.getKey().getName());
    }
    
    private Trigger buildJobTrigger(JobDetail detail, Date dataEsecuzione, String id) {
    	return TriggerBuilder.newTrigger()
				.forJob(detail)
				.withIdentity(detail.getKey().getName(), detail.getKey().getGroup())
				.withDescription(detail.getDescription())
				.startAt(Date.from(dataEsecuzione.toInstant()))
				.build();
	}
    
    private CronTrigger buildCronJobTrigger(JobDetail detail, String cron) {
    	return buildCronJobTrigger(detail, cron, detail.getKey().getName());
	}
    
    private CronTrigger buildCronJobTrigger(JobDetail detail, String cron, String id) {
    	return TriggerBuilder.newTrigger()
				.forJob(detail)
				.withIdentity(id, detail.getKey().getGroup())
				.withDescription(detail.getDescription())
				.withSchedule(CronScheduleBuilder.cronSchedule(cron))
				.build();
	}
}
