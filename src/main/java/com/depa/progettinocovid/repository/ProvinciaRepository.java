package com.depa.progettinocovid.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.depa.progettinocovid.models.Provincia;

/* interfaccia per interagire col repository Postgres dove vengono salvate informazioni riguardanti le province della Lombardia
 * per poter associare il nome alla sigla*/
@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, String>{
	
	public List<Provincia> findByNome(String nome);
	public Provincia findBySigla(String sigla);
}
