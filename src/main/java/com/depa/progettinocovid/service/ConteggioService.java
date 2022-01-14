package com.depa.progettinocovid.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.depa.progettinocovid.mapper.ConteggioMapper;
import com.depa.progettinocovid.models.ConteggioDto;
import com.depa.progettinocovid.repository.ConteggioRepository;

/** servizio per interagire col repository Mongo dei conteggio. contiene anche metodi per decorare il conteggio di sigla e timestamp
 * */
@Service
public class ConteggioService {
	
	@Autowired
	private ConteggioRepository repository;
	
	@Autowired
	private AssociazioneProvinceService associatore;
	
	public void save(ConteggioDto p) {
		repository.save(ConteggioMapper.INSTANCE.mapToEntity(p));
	}
	
	public void deleteAll() {
		repository.deleteAll();
	}
	
	public void addSigla(ConteggioDto c) {
		c.setSigla(associatore.getSigla(c.getProvincia()));
	}
	
	public void addData(ConteggioDto c) {
		c.setData(new Date());
	}
}
