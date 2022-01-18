package com.depa.progettinocovid.DataAggregator.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import commons.model.Somministrazione;

/**
 *  interfaccia per interagire col repository Mongo che salva i dati provenienti dall'API socrata
 * sulle dosi somministrate in ogni comune della Lombardia {@link com.depa.progettinocovid.exceptions.ControllerAdvisor}
 */
@Repository
public interface SomministrazioneRepository extends MongoRepository<Somministrazione, String> {
	
	<S extends Somministrazione> S save(S entity);
	
	<S extends Somministrazione> List<S> findBySigla(String sigla);
	
//	restituisce numero di oggetti spazzati
	Long deleteByCodice(String codice_comune);
}