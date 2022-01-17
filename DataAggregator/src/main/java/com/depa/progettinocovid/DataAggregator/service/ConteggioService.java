package com.depa.progettinocovid.DataAggregator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.depa.progettinocovid.DataAggregator.mapper.ConteggioMapper;
import com.depa.progettinocovid.DataAggregator.models.ConteggioDto;
import com.depa.progettinocovid.DataAggregator.repository.ConteggioRepository;

/** servizio per interagire col repository Mongo dei conteggio. contiene anche metodi per decorare il conteggio di sigla e timestamp
 * */
@Service
public class ConteggioService {
	
	@Autowired
	private ConteggioRepository repository;
	
	public void save(ConteggioDto p) {
		repository.save(ConteggioMapper.INSTANCE.mapToEntity(p));
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
