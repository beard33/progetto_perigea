package com.depa.progettinocovid.taskScheduler.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ExtractorRestClient {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${extractor.endpoint}")
	private String endpoint;
	
	public void mandaRichiestaExtractor (String tema) {
		restTemplate.getForObject(endpoint + tema, null);
	}
	
}
