package com.depa.progettinocovid.DataExtractor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.depa.progettinocovid.DataExtractor.rest.StatiCliniciRestClient;

import commons.model.StatiCliniciDto;

@Service
public class StatiCliniciService implements GenericService {
	
	private static List<StatiCliniciDto> lista = new ArrayList<StatiCliniciDto>();
	
	@Autowired
	private StatiCliniciRestClient client;
	
	@Autowired
	private KafkaService service;
	
	@Override
	public void prendiDati() {
		lista = client.prendiDati();
	}

	@Override
	public void pubblicaDati() {
		lista.forEach(e->service.sendStatiClinici(e));
	}
}
