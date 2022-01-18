package com.depa.progettinocovid.DataAggregator.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.depa.progettinocovid.DataAggregator.mapper.StatiCliniciMapper;
import com.depa.progettinocovid.DataAggregator.models.StatiCliniciDto;
import com.depa.progettinocovid.DataAggregator.repository.StatiCliniciRepository;

/** servizio per interagire col repository Mongo dei conteggio. contiene anche metodi per decorare il conteggio di sigla e timestamp
 * */
@Service
public class StatiCliniciService {
	
	@Autowired
	private StatiCliniciRepository repository;
	
	public void save(StatiCliniciDto p) {
		repository.save(StatiCliniciMapper.INSTANCE.mapToEntity(p));
	}
	
	public void deleteAll() {
		repository.deleteAll();
	}
	
	public boolean dateExists(Date date) {
		return repository.existsByData_inizio_sintomi(date);
	}
}
