package com.depa.progettinocovid.DataAggregator.service;

import java.util.Date;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

@Service
public class StatiCliniciAggregationService {
	
	@Autowired
	private MongoTemplate template;
	
	public Document sommaFiltra(String field, Date inizio, Date fine) {
		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.match(Criteria.where("dataInizioSintomi").gt(inizio).lt(fine)),
				Aggregation.group().sum(field).as(field + "_tot"));
		return template.aggregate(aggregation, "statiClinici", Object.class).getRawResults();
	}
}
