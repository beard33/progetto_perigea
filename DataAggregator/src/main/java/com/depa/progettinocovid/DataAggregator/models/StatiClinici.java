package com.depa.progettinocovid.DataAggregator.models;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class StatiClinici {
	
	@Field
	private Date data_inizio_sintomi;
	
	@Field
	private Integer casi_totali;
	
	@Field
	private Integer nessuno_stato_clinico;
	
	@Field
	private Integer solo_st_guarito;
	
	@Field
	private Integer solo_st_deceduto;
	
	@Field
	private Integer solo_st_asintomatico;
	
	@Field
	private Integer solo_st_lieve_pau_severo;
	
	@Field
	private Integer st_lieve_pau_severo_grave_g;
	
	@Field
	private Integer st_lieve_pau_severo_grave_d;
	
	@Field
	private Integer st_asintomatico_g;
	
	@Field
	private Integer st_asintomatico_d;
}
