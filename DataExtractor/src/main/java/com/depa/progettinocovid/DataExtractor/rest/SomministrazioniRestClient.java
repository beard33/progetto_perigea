package com.depa.progettinocovid.DataExtractor.rest;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import commons.mapper.SomministrazioneMapper;
import commons.model.Somministrazione;
import commons.model.SomministrazioneDto;

@Component
public class SomministrazioniRestClient {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${endpoint_vaccini}")
	private String endpoint;
	
	public List<SomministrazioneDto> prendiDati(){
		List<Somministrazione> vaccinationData = Arrays.asList(restTemplate.getForObject(
				endpoint, Somministrazione[].class));
		return SomministrazioneMapper.INSTANCE.mapToDtoList(vaccinationData);
	}
}
