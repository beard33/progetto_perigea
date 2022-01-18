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
public class Somministrazione {
	
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
	
}
