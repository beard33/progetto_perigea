package com.depa.progettinocovid.DataAggregator.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.depa.progettinocovid.DataAggregator.models.Conteggio;

/**
 *  interfaccia per interagire col repository Mongo che salva i dati provenienti dall'API socrata
 * sulle dosi somministrate in ogni comune della Lombardia {@link com.depa.progettinocovid.exceptions.ControllerAdvisor}
 */
@Repository
public interface ConteggioRepository extends MongoRepository<Conteggio, String> {
	
	<S extends Conteggio> S save(S entity);
	
	<S extends Conteggio> List<S> findBySigla(String sigla);
	
//	restituisce numero di oggetti spazzati
	Long deleteByCodice(String codice_comune);
}