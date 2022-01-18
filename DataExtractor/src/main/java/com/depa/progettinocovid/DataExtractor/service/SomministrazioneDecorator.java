package com.depa.progettinocovid.DataExtractor.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import commons.model.SomministrazioneDto;



@Service
public class SomministrazioneDecorator {
	
	@Autowired
	private ProvinceService provinceService;
	
	public void addSigla(SomministrazioneDto c) {
		String sigla = provinceService.getSigla(c.getProvincia());
		c.setSigla(sigla);
	}
	
	public void addDate(SomministrazioneDto c) {
		c.setData(new Date());
	}
	
}
