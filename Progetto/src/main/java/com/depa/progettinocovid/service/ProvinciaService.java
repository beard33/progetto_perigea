package com.depa.progettinocovid.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.depa.progettinocovid.models.Provincia;
import com.depa.progettinocovid.repository.ProvinciaRepository;

/** servizio per interagire col repository Postgres delle province
 * */
@Service
public class ProvinciaService {
	
	@Autowired
	ProvinciaRepository repository;
	
	public void save(Provincia p) {
		repository.save(p);
	}
}
