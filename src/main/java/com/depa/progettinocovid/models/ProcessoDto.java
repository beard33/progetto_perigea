package com.depa.progettinocovid.models;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProcessoDto {
	
	private Date inizio;
	private Date fine;
}
