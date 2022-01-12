 package com.depa.progettinocovid.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.depa.progettinocovid.models.Conteggio;
import com.depa.progettinocovid.repository.ConteggioRepository;

// prendi nuovi dati dall'endpoint e restituiscili
@Component
public class VacciniRestClient {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ConteggioRepository conteggioRepository;
	
	@Value("${endpoint_vaccini}")
	private String endpoint;
	
	public List<Conteggio> prendiDati() {
		try {
			conteggioRepository.deleteAll();
			return Arrays.asList(restTemplate.getForObject(endpoint, Conteggio[].class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ArrayList<Conteggio>();
	}
}
