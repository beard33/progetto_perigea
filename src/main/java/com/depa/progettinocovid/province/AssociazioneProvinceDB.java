package com.depa.progettinocovid.province;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.depa.progettinocovid.models.Provincia;
import com.depa.progettinocovid.repository.ProvinciaRepository;

@Service
public class AssociazioneProvinceDB {
	
	@Autowired
	private ProvinciaRepository repository;
	
	public String getSigla(String provincia) {
		return repository.findByNome(provincia.toLowerCase()).get(0).getSigla();
//		return repository.list(
//					String.format("SELECT id, sigla FROM province WHERE LOWER(nome)='%s'", provincia.toLowerCase()),
//					Provincia.class)
//				.stream().findFirst().get().getSigla();
	}
	
	public void put(List<Provincia> province) {
		province.stream().forEach(p->repository.save(p));
	}
	
	public boolean tableEmpty () {
		return repository.findAll().isEmpty();
	}
}
