package com.depa.progettinocovid.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.depa.progettinocovid.models.Processo;

@Repository
public interface ProcessoRepository extends MongoRepository<Processo, String> {
	
	<S extends Processo> S save(S entity);
	
//	@Query("{email:'?0'}")
//	List<Conteggio> findByEmail(String email);
}