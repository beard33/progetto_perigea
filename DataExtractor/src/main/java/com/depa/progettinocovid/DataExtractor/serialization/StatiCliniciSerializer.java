package com.depa.progettinocovid.DataExtractor.serialization;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import commons.model.StatiCliniciDto;

public class StatiCliniciSerializer implements Serializer<StatiCliniciDto>{

	@Override
	public void configure(Map<String, ?> map, boolean b) {}
	
	@Override
	public byte[] serialize(StatiCliniciDto s) {
		byte[] retVal = null;
	    ObjectMapper objectMapper = new ObjectMapper();
	    try {
	      retVal = objectMapper.writeValueAsString(s).getBytes();
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return retVal;
	}
	
	@Override
	public void close() {}

	@Override
	public byte[] serialize(String topic, StatiCliniciDto data) {
		return serialize(data);
	}
}
