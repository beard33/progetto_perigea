package com.depa.progettinocovid.taskScheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import com.depa.progettinocovid.taskScheduler.service.SchedulerService;

public class AvvioRunner implements ApplicationRunner {
	
	@Autowired
	SchedulerService schedulerService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		schedulerService.rescheduleAvvio();
	}

}
