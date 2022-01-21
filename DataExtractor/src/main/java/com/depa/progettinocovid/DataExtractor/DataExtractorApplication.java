package com.depa.progettinocovid.DataExtractor;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableJpaRepositories("com.depa.progettinocovid.DataExtractor.repository")
public class DataExtractorApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataExtractorApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	@Bean
	public ThreadPoolExecutor threadExecutor() {
		return (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
	}
}
