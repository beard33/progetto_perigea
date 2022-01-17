package com.depa.progettinocovid.DataAggregator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.depa.progettinocovid.DataAggregator.repository.ConteggioRepository;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = ConteggioRepository.class)
public class DataAggregatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataAggregatorApplication.class, args);
	}

}
