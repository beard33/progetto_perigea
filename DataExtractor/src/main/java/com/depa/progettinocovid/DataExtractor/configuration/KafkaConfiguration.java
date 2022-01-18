package com.depa.progettinocovid.DataExtractor.configuration;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.depa.progettinocovid.DataExtractor.model.ConteggioDto;
import com.depa.progettinocovid.DataExtractor.model.StatiCliniciDto;


@Configuration
public class KafkaConfiguration {

	//@Value("${kafka.host}")
	public static String BROKERS = "localhost:9092";
	
	@Bean
	public KafkaProducer<String, ConteggioDto> somministrazioniProducer () {
		return new KafkaProducer<>(getSomministrazioniProducerProps());
	}

	@Bean
	public KafkaProducer<String, StatiCliniciDto> statiCliniciProducer () {
		return new KafkaProducer<>(getSomministrazioniProducerProps());
	}

	public Properties getSomministrazioniProducerProps() {
		Properties props = new Properties();
		props.put("bootstrap.servers", BROKERS);
		props.put("acks", "all");
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "com.depa.progettinocovid.DataExtractor.serialization.ConteggioSerializer");
		return props;
	}

	public Properties getStatiCliniciProducerProps() {
		Properties props = new Properties();
		props.put("bootstrap.servers", BROKERS);
		props.put("acks", "all");
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		//TODO cambiare!!!
		props.put("value.serializer", "com.depa.progettinocovid.DataExtractor.serialization.ConteggioSerializer");
		return props;
	}
	
}
