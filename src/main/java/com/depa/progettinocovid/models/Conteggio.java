package com.depa.progettinocovid.models;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document
public class Conteggio {
	
	@JsonProperty("codistat_comune_dom")
	@Field
	private String codice;
	
	@JsonProperty("comune_dom")
	@Field
	private String comune;
	
	@JsonProperty("provincia_dom")
	@Field
	private String provincia;
	
	@Field
	private String sigla;
	
	@Field
	private Date data;
	
	@JsonProperty("tot_dose1")
	@Field
	private int dose1;
	
	@JsonProperty("tot_dose2")
	@Field
	private int dose2;
	
	public Conteggio () {}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getComune() {
		return comune;
	}

	public void setComune(String comune) {
		this.comune = comune;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getDose1() {
		return dose1;
	}

	public void setDose1(int dose1) {
		this.dose1 = dose1;
	}

	public int getDose2() {
		return dose2;
	}

	public void setDose2(int dose2) {
		this.dose2 = dose2;
	}
	
	
}
