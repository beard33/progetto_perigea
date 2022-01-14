package com.depa.progettinocovid.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.depa.progettinocovid.models.Processo;

/* interfaccia per inteagire col repository Mongo dove ogno volta che viene contatta l'API si registra quanto dura la chiamata
 * */
@Repository
public interface ProcessoRepository extends MongoRepository<Processo, String> {
	
	<S extends Processo> S save(S entity);
	
//	seleziona i processi iniziati nel giorno indicato
	List<Processo> findByInizioBetween(Date prima, Date dopo);
}