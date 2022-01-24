package com.depa.progettinocovid.DataExtractor.exceptions;

import java.util.Date;

public class DateOutOfRangeException extends BadRequestException {

	private static final long serialVersionUID = 1L;

	public DateOutOfRangeException(Date date) {
		super(date + " is out of [Date - Today] range");
	}
}
