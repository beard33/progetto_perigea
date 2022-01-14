package com.depa.progettinocovid.serialization;

import java.util.Map;

import org.springframework.stereotype.Component;
import com.depa.progettinocovid.models.ConteggioDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ConteggioDeserializer implements Deserializer<ConteggioDto> {

	@Override
	public void configure(Map<String, ?> map, boolean b) {}

	@Override
	public ConteggioDto deserialize(byte[] byteString) {
		ObjectMapper mapper = new ObjectMapper();
	    ConteggioDto user = null;
	    try {
	      user = mapper.readValue(byteString, ConteggioDto.class);
	    } catch (Exception e) {

	      e.printStackTrace();
	    }
	    return user;
	}

	@Override
	public void close() {}

	@Override
	public ConteggioDto deserialize(String topic, byte[] data) {
		return deserialize(data);
	}

}
