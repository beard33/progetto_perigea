package com.depa.progettinocovid.DataExtractor.service;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.depa.progettinocovid.DataExtractor.model.ConteggioDto;
import com.depa.progettinocovid.DataExtractor.model.StatiCliniciDto;

@Service
public class KafkaService {
	
	@Autowired
	private KafkaProducer<String, ConteggioDto> somministrazioniProducer;
	
	@Autowired
	private KafkaProducer<String, StatiCliniciDto> statiCliniciProducer;
	
	@Value("${kafka.topic_somministrazioni}")
	private String somministrazioniTopic;
	
	@Value("${kafka.topic_staticlinici}")
	private String staticliniciTopic;
	
	public void closeSomministrazioni() {
		somministrazioniProducer.close();
	}
	
	public void closeStatiClinici() {
		statiCliniciProducer.close();
	}
	
	public void send(ConteggioDto c) {
		try {
			somministrazioniProducer.send(new ProducerRecord<String, ConteggioDto>(somministrazioniTopic, c));
		} catch (Exception e) {
		   e.printStackTrace();
		}
	}
	
	public void send(StatiCliniciDto c) {
		try {
			statiCliniciProducer.send(new ProducerRecord<String, StatiCliniciDto>(staticliniciTopic, c));
		} catch (Exception e) {
		   e.printStackTrace();
		}
	}
}
