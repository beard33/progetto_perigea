package com.depa.progettinocovid.DataExtractor.model;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StatiCliniciDto {
	
	private Date dataInizioSintomi;
	private Integer casiTotali;
	private Integer nessunoStatoClinico;
	private Integer soloStGuarito;
	private Integer soloStDeceduto;
	private Integer soloStAsintomatico;
	private Integer soloStLievePauSevero;
	private Integer stLievePauSeveroGraveG;
	private Integer stLievePauSeveroGraveD;
	private Integer stAsintomaticoG;
	private Integer stAsintomaticoD;
	
}
