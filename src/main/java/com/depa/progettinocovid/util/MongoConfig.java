package com.depa.progettinocovid.util;

import java.util.Collection;
import java.util.Collections;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.depa.progettinocovid.repository.ProcessoRepository;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

/* questa classe config contiene la configurazione del database Mongo
 * */
@Configuration
@EnableMongoRepositories(basePackages = "com.depa")
@EnableJpaRepositories(excludeFilters = 
@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = ProcessoRepository.class))
public class MongoConfig extends AbstractMongoClientConfiguration {
 
    @Override
    protected String getDatabaseName() {
        return "test";
    }
 
    @Override
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017/test");
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build();
        
        return MongoClients.create(mongoClientSettings);
    }
 
    @Override
    public Collection<String> getMappingBasePackages() {
        return Collections.singleton("com.depa");
    }
}