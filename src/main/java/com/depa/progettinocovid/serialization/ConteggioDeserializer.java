package com.depa.progettinocovid.serialization;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.depa.progettinocovid.models.Conteggio;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ConteggioDeserializer implements Deserializer<Conteggio> {

	@Override
	public void configure(Map<String, ?> map, boolean b) {}

	@Override
	public Conteggio deserialize(byte[] byteString) {
		ObjectMapper mapper = new ObjectMapper();
	    Conteggio user = null;
	    try {
	      user = mapper.readValue(byteString, Conteggio.class);
	    } catch (Exception e) {

	      e.printStackTrace();
	    }
	    return user;
	}

	@Override
	public void close() {}

	@Override
	public Conteggio deserialize(String topic, byte[] data) {
		return deserialize(data);
	}

}
