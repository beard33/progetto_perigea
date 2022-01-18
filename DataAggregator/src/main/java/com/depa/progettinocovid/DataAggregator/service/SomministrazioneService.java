package com.depa.progettinocovid.DataAggregator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.depa.progettinocovid.DataAggregator.repository.SomministrazioneRepository;

import commons.mapper.SomministrazioneMapper;
import commons.model.SomministrazioneDto;

/** servizio per interagire col repository Mongo dei conteggio. contiene anche metodi per decorare il conteggio di sigla e timestamp
 * */
@Service
public class SomministrazioneService {
	
	@Autowired
	private SomministrazioneRepository repository;
	
	public void save(SomministrazioneDto p) {
		repository.save(SomministrazioneMapper.INSTANCE.mapToEntity(p));
	}
	
	public void deleteAll() {
		repository.deleteAll();
	}
	
	public boolean siglaEsiste(String sigla) {
		return !repository.findBySigla(sigla).isEmpty();
	}
	
	public void deleteIfPresent(String codice_comune) {
		repository.deleteByCodice(codice_comune);
	}
}
