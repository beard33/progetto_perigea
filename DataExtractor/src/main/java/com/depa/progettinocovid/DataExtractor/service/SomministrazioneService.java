package com.depa.progettinocovid.DataExtractor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.depa.progettinocovid.DataExtractor.rest.SomministrazioniRestClient;

import commons.model.SomministrazioneDto;

@Service
public class SomministrazioneService implements GenericService {
	
	private List<SomministrazioneDto> lista;
	
	@Autowired
	SomministrazioniRestClient client;
	
	@Autowired
	KafkaService service;
	
	@Autowired
	private SomministrazioneDecorator conteggioService;
	
	@Override
	public void prendiDati() {
		lista = client.prendiDati();
	}

	@Override
	public void pubblicaDati() {
		lista.forEach(e-> {
			decorate(e);
			service.send(e);
			});
	}
	
	private void decorate(SomministrazioneDto e) {
		conteggioService.addSigla(e);
		conteggioService.addDate(e);
	}

}
