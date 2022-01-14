package com.depa.progettinocovid.service;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.depa.progettinocovid.mapper.ConteggioMapper;
import com.depa.progettinocovid.models.ConteggioDto;
import com.depa.progettinocovid.repository.ConteggioRepository;
import com.depa.progettinocovid.serialization.ConteggioDeserializer;

/** questo servizio contiene il metodo {@link send(ConteggioDto c)} per mandare un messaggio al Kafka cluster
 * e il metodo {@link inoltra(String cString)} che è il consumer dei messaggi e salva i dati nel repo
 * */
@Service
public class KafkaService {
	
	@Autowired
	private ConteggioRepository repository;
	
	@Autowired
	private ConteggioDeserializer conteggioDeserializer;
	
	@Autowired
	private KafkaProducer<String, ConteggioDto> kafkaProducer;
	
	@Value("${topic}")
	private String topic;
	
//	TODO il producer va distrutto quando si ha finito di usarlo. come?
	public void send(ConteggioDto c) {
		try {
		   kafkaProducer.send(new ProducerRecord<String, ConteggioDto>(topic, c));
		} catch (Exception e) {
		   e.printStackTrace();
		}
	}
	
//	TODO farlo serializzare a kafka
	@KafkaListener(topics = {"${topic}"}, groupId = "test-consumer-group")
	public void inoltra (@Payload String cString) {
		repository.save(ConteggioMapper.INSTANCE.mapToEntity(conteggioDeserializer.deserialize(cString.getBytes())));
	}
}
