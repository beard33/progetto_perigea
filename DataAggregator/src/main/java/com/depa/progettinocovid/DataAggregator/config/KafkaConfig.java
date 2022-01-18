package com.depa.progettinocovid.DataAggregator.config;

import java.util.Properties;

import org.springframework.context.annotation.Configuration;

/** configurazione per il producer e il consumer kafka
 * */
@Configuration
public class KafkaConfig {
	public static final String BROKERS = "localhost:9092";
	
	public Properties getConsumerProps() {
		Properties props = new Properties();
		props.setProperty("bootstrap.servers", BROKERS);
		props.setProperty("group.id", "testGroup");
		props.setProperty("enable.auto.commit", "false");
		props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.setProperty("value.deserializer", "com.depa.progettinocovid.DataAggregator.serialization.SomministrazioneDeserializer");
		return props;
	}
}