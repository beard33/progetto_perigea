package com.depa.progettinocovid.serialization;

import java.util.Map;

public interface Deserializer<T> extends org.apache.kafka.common.serialization.Deserializer<T> {
	
	void configure(Map<String, ?> map, boolean b);
	
	T deserialize(byte[] byteString);
	
	void close();

}