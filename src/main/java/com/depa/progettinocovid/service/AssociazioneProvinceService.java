package com.depa.progettinocovid.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.depa.progettinocovid.models.Provincia;

@Service
public class AssociazioneProvinceService {
	
	@Autowired
	private AssociazioneProvinceDBService dbService;
	
	@Autowired
	private AssociazioneProvinceRestService restService;
	
	private boolean tableFilled = false;
	
	private void fillTable() {
		List<Provincia> province;
		if (dbService.tableEmpty()) {
			province = restService.get();
			dbService.put(province);
			tableFilled = true;
		}
	}
	
	public String getSigla(String provincia) {
		if (provincia.equalsIgnoreCase("ignota")) {
			return "XX";
		} else if (!tableFilled) {
			fillTable();
		}
		return dbService.getSigla(provincia);
	}

}
