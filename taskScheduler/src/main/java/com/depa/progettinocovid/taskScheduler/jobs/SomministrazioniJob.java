package com.depa.progettinocovid.taskScheduler.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.depa.progettinocovid.taskScheduler.rest.ExtractorRestClient;

@Component
public class SomministrazioniJob implements Job {

	@Value("${somministrazioni.tema}")
	private String tema;
	
	@Autowired
	private ExtractorRestClient extractorRestClient;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		extractorRestClient.mandaRichiestaExtractor(tema);
	}

}
