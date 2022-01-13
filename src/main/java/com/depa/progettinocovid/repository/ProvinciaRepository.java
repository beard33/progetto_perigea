package com.depa.progettinocovid.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.depa.progettinocovid.models.Provincia;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, String>{
	
	public List<Provincia> findByNome(String nome);
}
