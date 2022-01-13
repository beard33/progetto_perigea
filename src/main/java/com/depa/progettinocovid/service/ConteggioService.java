package com.depa.progettinocovid.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.depa.progettinocovid.models.Conteggio;
import com.depa.progettinocovid.repository.ConteggioRepository;

@Service
public class ConteggioService {
	
	@Autowired
	ConteggioRepository repository;
	
	public void save(Conteggio p) {
		repository.save(p);
	}
	
	public void deleteAll() {
		repository.deleteAll();
	}
	
	@Autowired
	private AssociazioneProvinceService associatore;
	
	public void addSigla(Conteggio c) {
		c.setSigla(associatore.getSigla(c.getProvincia()));
	}
	
	public void addData(Conteggio c) {
		c.setData(new Date());
	}
}
