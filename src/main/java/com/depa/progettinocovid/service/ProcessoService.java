package com.depa.progettinocovid.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.depa.progettinocovid.models.Processo;
import com.depa.progettinocovid.repository.ProcessoRepository;

/** servizio per interagire col repository Mongo dei processi.
 * */
@Service
public class ProcessoService {
	
	@Autowired
	ProcessoRepository repository;
	
	public void save(Processo p) {
		repository.save(p);
	}
	
	public List<Processo> findAll() {
		return repository.findAll();
	}
	
	public List<Processo> findByDate(Date prima) {
		return repository.findByInizioBetween(prima, new Date(prima.getTime() + 1000 * 60 * 60 * 24));
	}
}
