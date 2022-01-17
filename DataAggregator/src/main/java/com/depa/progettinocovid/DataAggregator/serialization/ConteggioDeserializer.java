package com.depa.progettinocovid.DataAggregator.serialization;
/**
 * Insieme a {@link com.depa.progettinocovid.serialization.ConteggioSerializer} è
 * responsabile della serializzazione e deserializzazione dei dati per la trasmissione
 * sul topic Kafka dedicato
 */
import java.util.Map;

import org.springframework.stereotype.Component;

import com.depa.progettinocovid.DataAggregator.models.ConteggioDto;
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
