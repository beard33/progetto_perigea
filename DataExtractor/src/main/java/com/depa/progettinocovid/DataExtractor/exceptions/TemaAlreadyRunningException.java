package com.depa.progettinocovid.DataExtractor.exceptions;

public class TemaAlreadyRunningException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public TemaAlreadyRunningException(String tema) {
		super(String.format("Request for %s already running", tema));
	}
}
