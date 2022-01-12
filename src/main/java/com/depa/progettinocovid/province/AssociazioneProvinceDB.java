package com.depa.progettinocovid.province;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.depa.progettinocovid.models.Provincia;
import com.depa.progettinocovid.repository.ProvinciaRepository;

@Component
public class AssociazioneProvinceDB {
	
	@Autowired
	ProvinciaRepository repository;
	
	public String getSigla(String provincia) {
		return repository.list(
					String.format("SELECT id, sigla FROM province WHERE LOWER(nome)='%s'", provincia.toLowerCase()),
					Provincia.class)
				.stream().findFirst().get().getSigla();
	}
	
	public void put(List<Provincia> province) {
		province.stream().forEach(p->repository.put(p));
	}
	
	public boolean tableEmpty () {
		return repository.isEmpty();
	}
}
