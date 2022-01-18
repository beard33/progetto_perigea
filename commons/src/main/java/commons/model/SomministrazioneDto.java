package commons.model;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SomministrazioneDto {
	
	private String codice;
	private String comune;
	private String provincia;
	private String sigla;
	private Date data;
	private int dose1;
	private int dose2;
}
