package com.depa.progettinocovid.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.depa.progettinocovid.models.Conteggio;
import com.depa.progettinocovid.province.AssociazioneProvinceManager;

@Component
public class ConteggioHelper {
	
	@Autowired
	private AssociazioneProvinceManager associazioneProvinceManager;
	
	public void addSigla(Conteggio c) {
		c.setSigla(associazioneProvinceManager.getSigla(c.getProvincia()));
	}
	
	public void addData(Conteggio c) {
		c.setData(new Date());
	}
}
