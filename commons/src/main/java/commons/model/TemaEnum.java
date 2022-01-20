package commons.model;

import lombok.Getter;

@Getter
public enum TemaEnum {

	SOMMINISTRAZIONI("somministrazioni"),
	STATI_CLINICI("stati_clinici");

	private String tema;
	
	private TemaEnum(String tema) {
		this.tema = tema;
	}
	
	
}
