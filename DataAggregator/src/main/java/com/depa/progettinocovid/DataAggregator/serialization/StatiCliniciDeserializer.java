package com.depa.progettinocovid.DataAggregator.serialization;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import commons.model.StatiCliniciDto;

@Component
public class StatiCliniciDeserializer implements Deserializer<StatiCliniciDto> {

	@Override
	public void configure(Map<String, ?> map, boolean b) {}

	@Override
	public StatiCliniciDto deserialize(byte[] byteString) {
		ObjectMapper mapper = new ObjectMapper();
		StatiCliniciDto user = null;
	    try {
	      user = mapper.readValue(byteString, StatiCliniciDto.class);
	    } catch (Exception e) {

	      e.printStackTrace();
	    }
	    return user;
	}

	@Override
	public void close() {}

	@Override
	public StatiCliniciDto deserialize(String topic, byte[] data) {
		return deserialize(data);
	}

}
