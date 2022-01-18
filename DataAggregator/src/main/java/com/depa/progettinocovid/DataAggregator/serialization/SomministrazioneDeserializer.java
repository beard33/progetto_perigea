package com.depa.progettinocovid.DataAggregator.serialization;
/**
 * Insieme a {@link com.depa.progettinocovid.serialization.ConteggioSerializer} è
 * responsabile della serializzazione e deserializzazione dei dati per la trasmissione
 * sul topic Kafka dedicato
 */
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import commons.model.SomministrazioneDto;

@Component
public class SomministrazioneDeserializer implements Deserializer<SomministrazioneDto> {

	@Override
	public void configure(Map<String, ?> map, boolean b) {}

	@Override
	public SomministrazioneDto deserialize(byte[] byteString) {
		ObjectMapper mapper = new ObjectMapper();
	    SomministrazioneDto user = null;
	    try {
	      user = mapper.readValue(byteString, SomministrazioneDto.class);
	    } catch (Exception e) {

	      e.printStackTrace();
	    }
	    return user;
	}

	@Override
	public void close() {}

	@Override
	public SomministrazioneDto deserialize(String topic, byte[] data) {
		return deserialize(data);
	}

}
