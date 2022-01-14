package com.depa.progettinocovid.serialization;

import java.util.Map;
import com.depa.progettinocovid.models.ConteggioDto;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConteggioSerializer implements Serializer<ConteggioDto> {

	@Override
	public void configure(Map<String, ?> map, boolean b) {}
	
	@Override
	public byte[] serialize(ConteggioDto c) {
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
	public byte[] serialize(String topic, ConteggioDto data) {
		return serialize(data);
	}
}
