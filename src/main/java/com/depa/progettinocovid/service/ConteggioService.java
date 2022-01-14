package com.depa.progettinocovid.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.depa.progettinocovid.models.Conteggio;
import com.depa.progettinocovid.models.ConteggioDto;
import com.depa.progettinocovid.repository.ConteggioRepository;

@Service
public class ConteggioService {
	
	@Autowired
	ConteggioRepository repository;
	
	public void save(ConteggioDto p) {
		repository.save(p);
	}
	
	public void deleteAll() {
		repository.deleteAll();
	}
	
	@Autowired
	private AssociazioneProvinceService associatore;
	
	public void addSigla(ConteggioDto c) {
		c.setSigla(associatore.getSigla(c.getProvincia()));
	}
	
	public void addData(ConteggioDto c) {
		c.setData(new Date());
	}
}
