package com.depa.progettinocovid.province;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.depa.progettinocovid.models.Provincia;

@Component
public class AssociazioneProvinceRestClient {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${endpoint_province}")
	private String endpoint;
	
	public List<Provincia> get() {
		return Arrays.asList(restTemplate.getForObject(endpoint, Provincia[].class));
	}
}
