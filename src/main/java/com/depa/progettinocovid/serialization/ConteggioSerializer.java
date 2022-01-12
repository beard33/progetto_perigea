package com.depa.progettinocovid.serialization;

import java.util.Map;

import com.depa.progettinocovid.models.Conteggio;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConteggioSerializer implements Serializer<Conteggio> {

	@Override
	public void configure(Map<String, ?> map, boolean b) {}
	
	@Override
	public byte[] serialize(Conteggio c) {
		byte[] retVal = null;
	    ObjectMapper objectMapper = new ObjectMapper();
	    try {
	      retVal = objectMapper.writeValueAsString(c).getBytes();
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return retVal;
	}

	@Override
	public void close() {}

	@Override
	public byte[] serialize(String topic, Conteggio data) {
		return serialize(data);
	}
}
