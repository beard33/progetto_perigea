package com.depa.progettinocovid.DataExtractor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.depa.progettinocovid.DataExtractor.model.ConteggioDto;
import com.depa.progettinocovid.DataExtractor.rest.VacciniRestClient;

@Service
public class SomministrazioniService implements GenericService {
	
	private List<ConteggioDto> lista;
	
	@Autowired
	VacciniRestClient client;
	
	@Autowired
	KafkaService service;
	
	@Autowired
	private ConteggioService conteggioService;
	
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
	
	private void decorate(ConteggioDto e) {
		conteggioService.addSigla(e);
		conteggioService.addDate(e);
	}

}
