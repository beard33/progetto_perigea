package com.depa.progettinocovid.province;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.depa.progettinocovid.models.Provincia;

@Component
public class AssociazioneProvinceManager {
	
	@Autowired
	AssociazioneProvinceDB associazioneProvinceDB;
	
	@Autowired
	AssociazioneProvinceRestClient associazioneProvinceRestClient;
	
	private boolean tableFilled = false;
	
	private void fillTable() {
		List<Provincia> province;
		if (associazioneProvinceDB.tableEmpty()) {
			province = associazioneProvinceRestClient.get();
			associazioneProvinceDB.put(province);
			tableFilled = true;
		}
	}
	
	public String getSigla(String provincia) {
		if (provincia.equalsIgnoreCase("ignota")) {
			return "XX";
		} else if (!tableFilled) {
			fillTable();
		}
		return associazioneProvinceDB.getSigla(provincia);
	}

}
