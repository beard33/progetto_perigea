package com.depa.progettinocovid.util;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.depa.progettinocovid.models.Conteggio;
import com.depa.progettinocovid.repository.ConteggioRepository;
import com.depa.progettinocovid.serialization.ConteggioDeserializer;

@Component
public class KafkaTransact {
	
	@Autowired
	private ConteggioRepository repository;
	
	@Autowired
	private KafkaConfig kafkaConfig;
	
	@Autowired
	private ConteggioDeserializer conteggioDeserializer;
	
	public void send(Conteggio c) {
		try (Producer<String, Conteggio> producer = new KafkaProducer<>(kafkaConfig.getProducerProps())) {
		   producer.send(new ProducerRecord<String, Conteggio>("vaccini", c));
		   producer.close();
		} catch (Exception e) {
		   e.printStackTrace();
		}
	}
	
	@KafkaListener(topics = {"${topic}"}, groupId = "test-consumer-group")
	public void inoltra (@Payload String cString) {
		repository.save(conteggioDeserializer.deserialize(cString.getBytes()));
	}
}
