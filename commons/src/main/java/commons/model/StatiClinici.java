package commons.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class StatiClinici {
	
	@JsonProperty("data_inizio_sintomi")
	@Field
	private Date dataInizioSintomi;
	
	@JsonProperty("casi_totali")
	@Field
	private Integer casiTotali;
	
	@JsonProperty("nessuno_stato_clinico")
	@Field
	private Integer nessunoStatoClinico;
	
	@JsonProperty("solo_st_guarito")
	@Field
	private Integer soloStGuarito;
	
	@JsonProperty("solo_st_deceduto")
	@Field
	private Integer soloStDeceduto;
	
	@JsonProperty("solo_st_asintomatico")
	@Field
	private Integer soloStAsintomatico;
	
	@JsonProperty("solo_st_lieve_pau_severo")
	@Field
	private Integer soloStLievePauSevero;
	
	@JsonProperty("st_lieve_pau_severo_grave_g")
	@Field
	private Integer stLievePauSeveroGraveG;
	
	@JsonProperty("st_lieve_pau_severo_grave_d")
	@Field
	private Integer stLievePauSeveroGraveD;
	
	@JsonProperty("st_asintomatico_g")
	@Field
	private Integer stAsintomaticoG;
	
	@JsonProperty("st_asintomatico_d")
	@Field
	private Integer stAsintomaticoD;
}
