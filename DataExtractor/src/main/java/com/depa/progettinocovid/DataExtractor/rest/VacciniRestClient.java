package com.depa.progettinocovid.DataExtractor.rest;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.depa.progettinocovid.DataExtractor.mapper.ConteggioMapper;
import com.depa.progettinocovid.DataExtractor.model.Conteggio;
import com.depa.progettinocovid.DataExtractor.model.ConteggioDto;

@Component
public class VacciniRestClient {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${endpoint_vaccini}")
	private String endpoint;
	
	public List<ConteggioDto> prendiDati(){
		List<Conteggio> vaccinationData = Arrays.asList(restTemplate.getForObject(
				endpoint, Conteggio[].class));
		return ConteggioMapper.INSTANCE.mapToDtoList(vaccinationData);
	}
}
