package com.depa.progettinocovid.DataExtractor.exceptions;

public class BadTemaRequestException extends BadRequestException {

	private static final long serialVersionUID = 1L;

	public BadTemaRequestException() {
		super("Tema must be of the accepted type");
	}
}
