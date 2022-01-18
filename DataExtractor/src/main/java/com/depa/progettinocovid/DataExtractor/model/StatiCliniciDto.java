package com.depa.progettinocovid.DataExtractor.model;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StatiCliniciDto {
	
	private Date data_inizio_sintomi;
	private Integer casi_totali;
	private Integer nessuno_stato_clinico;
	private Integer solo_st_guarito;
	private Integer solo_st_deceduto;
	private Integer solo_st_asintomatico;
	private Integer solo_st_lieve_pau_severo;
	private Integer st_lieve_pau_severo_grave_g;
	private Integer st_lieve_pau_severo_grave_d;
	private Integer st_asintomatico_g;
	private Integer st_asintomatico_d;
	
	@Override
	public String toString() {
		return "StatiCliniciDto [data_inizio_sintomi=" + data_inizio_sintomi + ", casi_totali=" + casi_totali
				+ ", nessuno_stato_clinico=" + nessuno_stato_clinico + ", solo_st_guarito=" + solo_st_guarito
				+ ", solo_st_deceduto=" + solo_st_deceduto + ", solo_st_asintomatico=" + solo_st_asintomatico
				+ ", solo_st_lieve_pau_severo=" + solo_st_lieve_pau_severo + ", st_lieve_pau_severo_grave_g="
				+ st_lieve_pau_severo_grave_g + ", st_lieve_pau_severo_grave_d=" + st_lieve_pau_severo_grave_d
				+ ", st_asintomatico_g=" + st_asintomatico_g + ", st_asintomatico_d=" + st_asintomatico_d + "]";
	}
	
	
}
