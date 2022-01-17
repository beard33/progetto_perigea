package com.depa.progettinocovid.serialization;

import java.util.Map;

public interface Serializer<T> extends org.apache.kafka.common.serialization.Serializer<T> {
	
	void configure(Map<String, ?> map, boolean b);
	
	byte[] serialize(T obj);
	
	void close();
}
