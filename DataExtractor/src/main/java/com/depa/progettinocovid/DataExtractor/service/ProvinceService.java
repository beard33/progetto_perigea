package com.depa.progettinocovid.DataExtractor.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.depa.progettinocovid.DataExtractor.model.Provincia;

@Service
public class ProvinceService {

	@Autowired
	private ProvinceDbService dbService;
	
	@Autowired
	private ProvinceRestService restService;
	
	private static HashMap<String, String> sigleProvince = new HashMap<>();

	public void fillTable() {
		dbService.put(restService.get());
	}
	
	private void fillMap() {
		if (dbService.tableEmpty()) {
			fillTable();
		}
		
		List<Provincia> listaProvince = dbService.getAllProvince();
		listaProvince.forEach(p->sigleProvince.put(p.getNome(), p.getSigla()));
	}
	
	public String getSigla(String provincia) {
		if (sigleProvince.isEmpty()) {
			fillMap();
		}
		
		return provincia.equalsIgnoreCase("ignota") ? "XX" : sigleProvince.get(provincia.toLowerCase());
	}
	
	public void emptyDB () {
		dbService.deleteAll();
	}

}
