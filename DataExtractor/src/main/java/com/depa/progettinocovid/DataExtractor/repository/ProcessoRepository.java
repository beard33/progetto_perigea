package com.depa.progettinocovid.DataExtractor.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.depa.progettinocovid.DataExtractor.model.Processo;

@Repository
public interface ProcessoRepository extends MongoRepository<Processo, String>{

	<S extends Processo> S save(S entity);
	List<Processo> findByInizioBetween(Date inizio, Date fine);
}
