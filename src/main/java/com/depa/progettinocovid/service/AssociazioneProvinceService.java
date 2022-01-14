package com.depa.progettinocovid.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** questo servizio funge da punto di ingresso univoco per associare il nome di una provincia alla sua sigla.
 * comunica con {@link AssociazioneProvinceRestService} se necessario, altrimenti interroga il DB
 * */
@Service
public class AssociazioneProvinceService {
	
	@Autowired
	private AssociazioneProvinceDBService dbService;
	
	@Autowired
	private AssociazioneProvinceRestService restService;
	
	public void fillTable() {
		dbService.put(restService.get());
	}
	
	public String getSigla(String provincia) {
		if (provincia.equalsIgnoreCase("ignota")) {
			return "XX";
		} else if (dbService.tableEmpty()) {
			fillTable();
		}
		return dbService.getSigla(provincia);
	}
	
	public void emptyDB () {
		dbService.deleteAll();
	}

}
