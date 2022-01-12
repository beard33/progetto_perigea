package com.depa.progettinocovid.models;

import javax.persistence.Column;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProvinciaDto {
	
	private int id;
	private int codice;
	private String nome;
	private String sigla;
	private String regione;
}
