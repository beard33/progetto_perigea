package com.depa.progettinocovid.util;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.depa.progettinocovid.models.ConteggioDto;

/** configurazione per il producer e il consumer kafka
 * */
@Configuration
public class KafkaConfig {
	public static final String BROKERS = "localhost:9092";
	
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
		props.put("value.serializer", "com.depa.progettinocovid.serialization.ConteggioSerializer");
		return props;
	}
	
	public Properties getConsumerProps() {
		Properties props = new Properties();
		props.setProperty("bootstrap.servers", BROKERS);
		props.setProperty("group.id", "testGroup");
		props.setProperty("enable.auto.commit", "false");
		props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.setProperty("value.deserializer", "com.depa.progettinocovid.serialization.ConteggioDeserializer");
		return props;
	}
}