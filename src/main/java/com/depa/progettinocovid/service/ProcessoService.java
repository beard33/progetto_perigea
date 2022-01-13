package com.depa.progettinocovid.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.depa.progettinocovid.models.Processo;
import com.depa.progettinocovid.repository.ProcessoRepository;

@Service
public class ProcessoService {
	
	@Autowired
	ProcessoRepository repository;
	
	public void save(Processo p) {
		repository.save(p);
	}
}
