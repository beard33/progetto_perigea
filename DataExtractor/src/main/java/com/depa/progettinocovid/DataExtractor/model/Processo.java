package com.depa.progettinocovid.DataExtractor.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
@Document
public class Processo {

	@Field
	private Date inizio;
	
	@Field 
	private Date fine;
	
	@Field
	private String tipo;
}
