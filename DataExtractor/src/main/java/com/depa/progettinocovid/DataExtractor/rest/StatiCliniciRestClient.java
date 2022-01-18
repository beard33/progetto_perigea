package com.depa.progettinocovid.DataExtractor.rest;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.depa.progettinocovid.DataExtractor.mapper.StatiCliniciMapper;
import com.depa.progettinocovid.DataExtractor.model.StatiClinici;
import com.depa.progettinocovid.DataExtractor.model.StatiCliniciDto;

@Component
public class StatiCliniciRestClient {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${endpoint_stati_clinici}")
	private String endpoint;

	public List<StatiCliniciDto> prendiDati() {
		List<StatiClinici> statiCliniciData = Arrays.asList(restTemplate.getForObject(
				endpoint, StatiClinici[].class));
		
		System.out.println(StatiCliniciMapper.INSTANCE.mapToDto(statiCliniciData.get(0)));
		return StatiCliniciMapper.INSTANCE.mapToDtoList(statiCliniciData);
	}

}
