package com.depa.progettinocovid.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.depa.progettinocovid.models.Conteggio;
import com.depa.progettinocovid.models.ConteggioDto;

@Repository
public interface ConteggioRepository extends MongoRepository<ConteggioDto, String> {
	
	<S extends ConteggioDto> S save(S entity);
}