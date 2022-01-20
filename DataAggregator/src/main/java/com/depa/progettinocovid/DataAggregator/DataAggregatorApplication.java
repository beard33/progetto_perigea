package com.depa.progettinocovid.DataAggregator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.bson.Document;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;

import commons.rest.Response;

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
	
	@Bean
	public List<Future<ResponseEntity<Response<Document>>>> futures () {
		return new ArrayList<Future<ResponseEntity<Response<Document>>>>();
	}

}
