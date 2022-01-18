package com.depa.progettinocovid.DataAggregator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.depa.progettinocovid.DataAggregator.models.ConteggioDto;
import com.depa.progettinocovid.DataAggregator.serialization.ConteggioDeserializer;

/** questo servizio contiene il metodo {@link send(ConteggioDto c)} per mandare un messaggio al Kafka cluster
 * e il metodo {@link inoltra(String cString)} che è il consumer dei messaggi e salva i dati nel repo
 * */
@Service
public class KafkaService {
	
	@Autowired
	private ConteggioService conteggioService;
	
	@Autowired
	private ConteggioDeserializer conteggioDeserializer;
	
//	TODO farlo serializzare a kafka
	@KafkaListener(topics = {"${kafka.topic_somministrazioni}"}, groupId = "test-consumer-group")
	public void inoltraSomministrazioni (@Payload String cString) {
		ConteggioDto c = conteggioDeserializer.deserialize(cString.getBytes());
		
		conteggioService.deleteIfPresent(c.getCodice());
		conteggioService.save(c);
	}
	
//	TODO farlo serializzare a kafka
	@KafkaListener(topics = {"${kafka.topic_staticlinici}"}, groupId = "test-consumer-group")
	public void inoltraStatiClinici (@Payload String cString) {
	}
}
