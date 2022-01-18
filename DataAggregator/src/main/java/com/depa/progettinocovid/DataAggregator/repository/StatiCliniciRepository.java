package com.depa.progettinocovid.DataAggregator.repository;

import java.util.Date;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.depa.progettinocovid.DataAggregator.models.StatiClinici;

@Repository
public interface StatiCliniciRepository extends MongoRepository<StatiClinici, String> {
	
	<S extends StatiClinici> S save(S entity);
	
//	restituisce numero di oggetti spazzati
//	Long deleteByDataInizioSintomi(Date data);
	
	Boolean existsByDataInizioSintomi(Date data);
}