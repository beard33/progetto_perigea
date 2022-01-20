package com.depa.progettinocovid.taskScheduler;

import java.util.List;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import com.depa.progettinocovid.taskScheduler.rest.ScheduledInfo;
import com.depa.progettinocovid.taskScheduler.service.ScheduledInfoRepositoryService;
import com.depa.progettinocovid.taskScheduler.service.SchedulerService;

public class AvvioRunner implements ApplicationRunner {
	
	@Autowired
	SchedulerService schedulerService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		schedulerService.rescheduleAvvio();
	}

}
