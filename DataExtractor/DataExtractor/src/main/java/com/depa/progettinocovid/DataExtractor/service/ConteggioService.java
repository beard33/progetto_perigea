package com.depa.progettinocovid.DataExtractor.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.depa.progettinocovid.DataExtractor.model.ConteggioDto;



@Service
public class ConteggioService {
	
	@Autowired
	private ProvinceService provinceService;
	
	public void addSigla(ConteggioDto c) {
		String sigla = provinceService.getSigla(c.getProvincia());
		c.setSigla(sigla);
	}
	
	public void addDate(ConteggioDto c) {
		c.setData(new Date());
	}
	
}
