package com.depa.progettinocovid.DataExtractor.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.depa.progettinocovid.DataExtractor.service.GenericService;
import com.depa.progettinocovid.DataExtractor.service.SomministrazioneService;
import com.depa.progettinocovid.DataExtractor.service.StatiCliniciService;

@Component
public class ServiceFactory implements AbstractFactory {
	
	@Autowired
	private SomministrazioneService somministrazioniService;
	
	@Autowired
	private StatiCliniciService statiCliniciService;
	
	// TODO ENUM
	public GenericService getService(String type) {
		System.out.println(type);
		if (type.equals("somministrazioni")) {
			return somministrazioniService;
		} else if (type.equals("stati-clinici")) {
			return statiCliniciService;
		}
		
		return null;
	}
}
