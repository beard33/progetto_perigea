package com.depa.progettinocovid.DataAggregator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//@EnableMongoRepositories(basePackageClasses = ConteggioRepository.class)
public class DataAggregatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataAggregatorApplication.class, args);
	}
	
	@Bean
	public ExecutorService executorService () {
		return Executors.newFixedThreadPool(10);
	}
}
