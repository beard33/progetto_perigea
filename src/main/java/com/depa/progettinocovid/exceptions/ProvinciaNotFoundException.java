package com.depa.progettinocovid.exceptions;

public class ProvinciaNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ProvinciaNotFoundException(String provincia) {
		super(String.format("Provincia %s non trovata", provincia));
	}
	
}
