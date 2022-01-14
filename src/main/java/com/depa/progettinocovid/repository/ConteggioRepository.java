package com.depa.progettinocovid.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.depa.progettinocovid.models.ConteggioDto;


/* interfaccia per interagire col repository Mongo che salva i dati provenienti dall'API socrata
 * sulle dosi somministrate in ogni comune della Lombardia
 */
@Repository
public interface ConteggioRepository extends MongoRepository<ConteggioDto, String> {
	
	<S extends ConteggioDto> S save(S entity);
}