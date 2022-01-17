package com.depa.progettinocovid.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.depa.progettinocovid.models.Provincia;

/** questo servizio funge da punto di ingresso univoco per associare il nome di una provincia alla sua sigla.
 * comunica con {@link AssociazioneProvinceRestService} se necessario, altrimenti interroga il DB
 * */
@Service
public class AssociazioneProvinceService {
	
	@Autowired
	private AssociazioneProvinceDBService dbService;
	
	@Autowired
	private AssociazioneProvinceRestService restService;
	
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
