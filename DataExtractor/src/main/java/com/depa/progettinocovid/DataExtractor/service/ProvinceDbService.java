package com.depa.progettinocovid.DataExtractor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.depa.progettinocovid.DataExtractor.model.Provincia;
import com.depa.progettinocovid.DataExtractor.repository.ProvinciaRepository;

@Service
public class ProvinceDbService {
	
	@Autowired
	private ProvinciaRepository repository;
	
	public void put(List<Provincia> province) {
		province.stream().forEach(p->repository.save(p));
	}
	
	public List<Provincia> getAllProvince(){
		return repository.findAll();
	}
	
	public boolean tableEmpty () {
		return repository.findAll().isEmpty();
	}
	
	public void deleteAll() {
		repository.deleteAll();
	}
	
}
