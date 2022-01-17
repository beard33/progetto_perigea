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
	private ConteggioService service;
	
	@Autowired
	private ConteggioDeserializer conteggioDeserializer;
	
//	TODO farlo serializzare a kafka
	@KafkaListener(topics = {"${topic}"}, groupId = "test-consumer-group")
	public void inoltra (@Payload String cString) {
		ConteggioDto c = conteggioDeserializer.deserialize(cString.getBytes());
		
		service.deleteIfPresent(c.getCodice());
		service.save(c);
	}
}
