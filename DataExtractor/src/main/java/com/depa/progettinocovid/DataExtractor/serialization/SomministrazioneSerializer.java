package com.depa.progettinocovid.DataExtractor.serialization;
/**
 * Insieme a {@link com.depa.progettinocovid.serialization.ConteggioDeserializer} è
 * responsabile della serializzazione e deserializzazione dei dati per la trasmissione
 * sul topic Kafka dedicato
 */
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import commons.model.SomministrazioneDto;

public class SomministrazioneSerializer implements Serializer<SomministrazioneDto> {

	@Override
	public void configure(Map<String, ?> map, boolean b) {}
	
	@Override
	public byte[] serialize(SomministrazioneDto c) {
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
	public byte[] serialize(String topic, SomministrazioneDto data) {
		return serialize(data);
	}
}
