package com.depa.progettinocovid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.depa.progettinocovid.util.TopicCreator;

@SpringBootApplication
public class ProgettinocovidApplication {
	
//	TODO non puo essere static per ora hardwiro
//	@Value("${topic}")
//	private static String topic;

	public static void main(String[] args) {
		try {
			TopicCreator.createTopic("vaccini", 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		SpringApplication.run(ProgettinocovidApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}
