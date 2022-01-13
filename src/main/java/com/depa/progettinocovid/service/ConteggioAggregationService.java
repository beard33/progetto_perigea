package com.depa.progettinocovid.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

@Service
public class ConteggioAggregationService {
	
	@Autowired
	MongoTemplate template;
	
	public enum Capo {min, max}
	
	public AggregationResults<Object> somma(String field) {
		return template.aggregate(Aggregation.newAggregation(Aggregation.group().sum(field).as(field + "_tot")), "conteggio", Object.class);
	}
	
	public AggregationResults<Object> sommaFiltra(String field, String sigla) {
		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.match(Criteria.where("sigla").is(sigla)),
				Aggregation.group().sum(field).as(field + "_tot"));
		return template.aggregate(aggregation, "conteggio", Object.class);
	}
	
	public AggregationResults<Object> capoSomma(String field, Capo capo) {
		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.sort(Sort.by(capo == Capo.max ? Direction.DESC : Direction.ASC, field)),
				Aggregation.limit(1));
		return template.aggregate(aggregation, "conteggio", Object.class);
	}
	
	public AggregationResults<Object> capoSommaFiltra(String field, Capo capo, String sigla) {
		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.match(Criteria.where("sigla").is(sigla)),
				Aggregation.sort(Sort.by(capo == Capo.max ? Direction.DESC : Direction.ASC, field)),
				Aggregation.limit(1));
		return template.aggregate(aggregation, "conteggio", Object.class);
	}
	
	public AggregationResults<Object> listaDoppia () {
		Aggregation aggregation = Aggregation.newAggregation(Aggregation.sort(Sort.by(Direction.DESC, "dose2")));
		return template.aggregate(aggregation, "conteggio", Object.class);
	}
	
	public AggregationResults<Object> listaDoppiaFiltra (String sigla) {
		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.match(Criteria.where("sigla").is(sigla)),
				Aggregation.sort(Sort.by(Direction.DESC, "dose2")));
		return template.aggregate(aggregation, "conteggio", Object.class);
	}
}
