package com.depa.progettinocovid.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.depa.progettinocovid.models.Conteggio;

public interface ConteggioRepository extends MongoRepository<Conteggio, String> {
	
	<S extends Conteggio> S save(S entity);
	
//	@Query("{email:'?0'}")
//	List<Conteggio> findByEmail(String email);
}