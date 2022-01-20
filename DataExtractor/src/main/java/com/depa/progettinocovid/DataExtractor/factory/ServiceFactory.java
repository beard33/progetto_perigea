package com.depa.progettinocovid.DataExtractor.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.depa.progettinocovid.DataExtractor.service.GenericService;
import com.depa.progettinocovid.DataExtractor.service.SomministrazioneService;
import com.depa.progettinocovid.DataExtractor.service.StatiCliniciService;

import commons.model.TemaEnum;

@Component
public class ServiceFactory implements AbstractFactory {
	
	@Autowired
	private SomministrazioneService somministrazioniService;
	
	@Autowired
	private StatiCliniciService statiCliniciService;
	
	// TODO ENUM
	public GenericService getService(TemaEnum type) {
		System.out.println(type);
		if (type.equals(TemaEnum.SOMMINISTRAZIONI)) {
			return somministrazioniService;
		} else if (type.equals(TemaEnum.STATI_CLINICI)) {
			return statiCliniciService;
		}
		
		return null;
	}
}
