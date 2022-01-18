package com.depa.progettinocovid.DataAggregator.exceptions;
/**
 * Eccezione custom per la gestione di un 404 not found relativo alla provincia
 * nel {@link com.depa.progettinocovid.SomministrazioniRestController.VacciniRestController}
 *
 */

public class ProvinciaNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ProvinciaNotFoundException(String provincia) {
		super(String.format("Provincia %s non trovata", provincia));
	}
	
}
