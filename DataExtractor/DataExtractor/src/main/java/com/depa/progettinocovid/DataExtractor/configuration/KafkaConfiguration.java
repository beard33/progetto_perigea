package com.depa.progettinocovid.DataExtractor.configuration;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.depa.progettinocovid.DataExtractor.model.ConteggioDto;


@Configuration
public class KafkaConfiguration {

	//@Value("${kafka.host}")
	public static String BROKERS = "localhost:9092";
	
	@Bean
	public KafkaProducer<String, ConteggioDto> kafkaProducer () {
		return new KafkaProducer<>(getProducerProps());
	}

	public Properties getProducerProps() {
		Properties props = new Properties();
		props.put("bootstrap.servers", BROKERS);
		props.put("acks", "all");
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "com.depa.progettinocovid.DataExtractor.serialization.ConteggioSerializer");
		return props;
	}
	
}
