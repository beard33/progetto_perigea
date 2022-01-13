package com.depa.progettinocovid.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.depa.progettinocovid.models.Conteggio;

@Repository
public interface ConteggioRepository extends MongoRepository<Conteggio, String> {
	
	<S extends Conteggio> S save(S entity);
}