package com.depa.progettinocovid.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.depa.progettinocovid.models.Provincia;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ProvinciaRepository {
	
	@Autowired
	private ObjectMapper mapper;
	
	private final JdbcTemplate jdbcTemplate ;
	
	@Autowired
	public ProvinciaRepository(JdbcTemplate jdbcTemplate) {
	   	this.jdbcTemplate = jdbcTemplate;
	}
	
	@Transactional
	public <T> List<T> list (String q, Class<T> entity) {
		List<Map<String, Object>> mappe = jdbcTemplate.queryForList(q);
		List<T> lista = new ArrayList<T>();
		mappe.forEach(m->lista.add(mapper.convertValue(m, entity)));
       return lista;
   }
	
	@Transactional
	public boolean isEmpty() {
		return list("SELECT * FROM province LIMIT 1", Provincia.class).isEmpty();
	}
	
//	TODO inserire p nel database. prima facevo getSession.save(p); ora questa soluzione mi sembra peggiore
	@Transactional
	public int put(Provincia p) {
		return jdbcTemplate.update("INSERT INTO province (id, codice, nome, sigla, regione) VALUES (?,?,?,?,?)",
				p.getId(),
				p.getCodice(),
				p.getNome(),
				p.getSigla(),
				p.getRegione());
	}

}
