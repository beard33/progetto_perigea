package com.depa.progettinocovid.service;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.depa.progettinocovid.models.ConteggioDto;
import com.depa.progettinocovid.repository.ConteggioRepository;
import com.depa.progettinocovid.serialization.ConteggioDeserializer;
import com.depa.progettinocovid.util.KafkaConfig;

/** questo servizio contiene il metodo {@link send(ConteggioDto c)} per mandare un messaggio al Kafka cluster
 * e il metodo {@link inoltra(String cString)} che è il consumer dei messaggi e salva i dati nel repo
 * */
@Service
public class KafkaService {
	
	@Autowired
	private ConteggioRepository repository;
	
	@Autowired
	private KafkaConfig kafkaConfig;
	
	@Autowired
	private ConteggioDeserializer conteggioDeserializer;
	
	@Value("${topic}")
	private String topic;
	
	public void send(ConteggioDto c) {
		try (Producer<String, ConteggioDto> producer = new KafkaProducer<>(kafkaConfig.getProducerProps())) {
		   producer.send(new ProducerRecord<String, ConteggioDto>(topic, c));
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
