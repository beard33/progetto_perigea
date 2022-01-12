package com.depa.progettinocovid.rest;

import lombok.Builder;
import lombok.Data;
import java.util.Date;
import java.text.SimpleDateFormat;

@Data
@Builder
public class Response<T> {

	public enum Type {
		SUCCESS,
		ERROR
	}
	
	private Type type;
	private int code;
	private String description;
	@Builder.Default
	private String timestamp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
		.format(new Date());
	private T body;
	
}
