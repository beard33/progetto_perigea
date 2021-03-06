package com.depa.progettinocovid.service;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

/** questo servizio contiene metodi generalizzati per aggregare in vari modi i dati sui conteggi
 * */
@Service
public class ConteggioAggregationService {
	
	@Autowired
	private MongoTemplate template;
	
	public enum Capo {min, max}
	
	public Document somma(String field) {
		return template.aggregate(Aggregation.newAggregation(Aggregation.group().sum(field).as(field + "_tot")), "conteggio", Object.class).getRawResults();
	}
	
	public Document sommaFiltra(String field, String sigla) {
		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.match(Criteria.where("sigla").is(sigla)),
				Aggregation.group().sum(field).as(field + "_tot"));
		return template.aggregate(aggregation, "conteggio", Object.class).getRawResults();
	}
	
	public Document capoSomma(String field, Capo capo) {
		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.sort(Sort.by(capo == Capo.max ? Direction.DESC : Direction.ASC, field)),
				Aggregation.limit(1));
		return template.aggregate(aggregation, "conteggio", Object.class).getRawResults();
	}
	
	public Document capoSommaFiltra(String field, Capo capo, String sigla) {
		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.match(Criteria.where("sigla").is(sigla)),
				Aggregation.sort(Sort.by(capo == Capo.max ? Direction.DESC : Direction.ASC, field)),
				Aggregation.limit(1));
		return template.aggregate(aggregation, "conteggio", Object.class).getRawResults();
	}
	
	public Document listaDoppia () {
		Aggregation aggregation = Aggregation.newAggregation(Aggregation.sort(Sort.by(Direction.DESC, "dose2")));
		return template.aggregate(aggregation, "conteggio", Object.class).getRawResults();
	}
	
	public Document listaDoppiaFiltra (String sigla) {
		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.match(Criteria.where("sigla").is(sigla)),
				Aggregation.sort(Sort.by(Direction.DESC, "dose2")));
		return template.aggregate(aggregation, "conteggio", Object.class).getRawResults();
	}
}
