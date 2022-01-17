package com.depa.progettinocovid.DataExtractor.service;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.depa.progettinocovid.DataExtractor.model.ConteggioDto;

@Service
public class KafkaService {
	
	@Autowired
	private KafkaProducer<String, ConteggioDto> kafkaProducer;
	
	@Value("${kafka.topic}")
	private String topic;
	
	public void closeKafka() {
		kafkaProducer.close();
	}
	
	public void send(ConteggioDto c) {
		try {
		   kafkaProducer.send(new ProducerRecord<String, ConteggioDto>(topic, c));
		} catch (Exception e) {
		   e.printStackTrace();
		}
	}
}
