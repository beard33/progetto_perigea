package com.depa.progettinocovid.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.depa.progettinocovid.models.Provincia;

/** servizio per accedere all'API delle province
 */
@Service
public class AssociazioneProvinceRestService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${endpoint_province}")
	private String endpoint;
	
	public List<Provincia> get() {
		return Arrays.asList(restTemplate.getForObject(endpoint, Provincia[].class));
	}
}
