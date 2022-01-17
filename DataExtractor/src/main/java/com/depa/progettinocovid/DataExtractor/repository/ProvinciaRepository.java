package com.depa.progettinocovid.DataExtractor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.depa.progettinocovid.DataExtractor.model.Provincia;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, String> {

	public List<Provincia> findByNome(String nome);
	public Provincia findBySigla(String sigla);
}
