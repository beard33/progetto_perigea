package com.depa.progettinocovid.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.depa.progettinocovid.models.Conteggio;

/**
 *  interfaccia per interagire col repository Mongo che salva i dati provenienti dall'API socrata
 * sulle dosi somministrate in ogni comune della Lombardia {@link com.depa.progettinocovid.exceptions.ControllerAdvisor}
 */
@Repository
public interface ConteggioRepository extends MongoRepository<Conteggio, String> {
	
	<S extends Conteggio> S save(S entity);
}