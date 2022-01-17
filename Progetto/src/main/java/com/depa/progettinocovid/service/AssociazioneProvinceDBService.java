package com.depa.progettinocovid.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.depa.progettinocovid.models.Provincia;
import com.depa.progettinocovid.repository.ProvinciaRepository;

/** servizio intermediario per accedere al repository delle province
 * */
@Service
public class AssociazioneProvinceDBService {
	
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
