package com.depa.progettinocovid.DataExtractor.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.depa.progettinocovid.DataExtractor.model.Provincia;

@Service
public class ProvinceRestService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${endpoint_province}")
	private String endpoint;
	
	public List<Provincia> get(){
		return Arrays.asList(restTemplate.getForObject(endpoint, Provincia[].class));
	}
}
