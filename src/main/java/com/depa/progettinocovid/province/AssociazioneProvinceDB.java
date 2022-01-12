package com.depa.progettinocovid.province;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.depa.progettinocovid.models.Provincia;
import com.depa.progettinocovid.util.HibernateUtil;

@Component
public class AssociazioneProvinceDB {
	
	@Autowired
	HibernateUtil hibernateUtil;
	
	public String getSigla(String provincia) {
		return ((List<Provincia>) hibernateUtil.list(
					String.format("SELECT id, sigla FROM province WHERE LOWER(nome)='%s'", provincia.toLowerCase()),
					Provincia.class))
				.stream().findFirst().get().getSigla();
	}
	
	public void put(List<Provincia> province) {
		province.stream().forEach(p->hibernateUtil.getSession().save(p));
		hibernateUtil.getSession().flush();
		hibernateUtil.getSession().getTransaction().commit();
	}
	
	public boolean tableEmpty () {
		return hibernateUtil.list("SELECT * FROM province LIMIT 1", Provincia.class).isEmpty();
	}
}
