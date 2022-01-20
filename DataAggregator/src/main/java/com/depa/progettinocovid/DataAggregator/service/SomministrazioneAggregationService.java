package com.depa.progettinocovid.DataAggregator.service;

import java.util.LinkedHashMap;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.depa.progettinocovid.DataAggregator.model.AggregationResult;

import commons.model.TemaEnum;

/** questo servizio contiene metodi generalizzati per aggregare in vari modi i dati sui conteggi
 * */
@Service
@SuppressWarnings("unchecked")
public class SomministrazioneAggregationService {
	
	@Autowired
	private MongoTemplate template;
	
	private static final String TEMA = TemaEnum.SOMMINISTRAZIONI.getTema();
	
	public enum Capo {min, max}
	
	public AggregationResult somma(String field) {
		
		Aggregation aggregation = Aggregation.newAggregation(Aggregation.group().sum(field).as(field + "_tot"));
		LinkedHashMap<String, Object> doc = (LinkedHashMap<String, Object>)
				template.aggregate(aggregation, TEMA, Object.class).getMappedResults().get(0);
		
		return new AggregationResult(field + "_tot", (Integer) doc.get(field + "_tot"));
	}
	
	public AggregationResult sommaFiltra(String field, String sigla) {
		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.match(Criteria.where("sigla").is(sigla)),
				Aggregation.group().sum(field).as(field + "_tot"));
		LinkedHashMap<String, Object> doc = (LinkedHashMap<String, Object>)
				template.aggregate(aggregation, TEMA, Object.class).getMappedResults().get(0);
		
		return new AggregationResult(field + "_tot", (Integer) doc.get(field + "_tot"));
	}
	
	public AggregationResult capoSomma(String field, Capo capo) {
		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.sort(Sort.by(capo == Capo.max ? Direction.DESC : Direction.ASC, field)),
				Aggregation.limit(1));
		LinkedHashMap<String, Object> doc = (LinkedHashMap<String, Object>)
				template.aggregate(aggregation, TEMA, Object.class).getMappedResults().get(0);
		
		return new AggregationResult(field + capo.name(), (Integer) doc.get(field));
	}
	
	public AggregationResult capoSommaFiltra(String field, Capo capo, String sigla) {
		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.match(Criteria.where("sigla").is(sigla)),
				Aggregation.sort(Sort.by(capo == Capo.max ? Direction.DESC : Direction.ASC, field)),
				Aggregation.limit(1));
		LinkedHashMap<String, Object> doc = (LinkedHashMap<String, Object>)
				template.aggregate(aggregation, TEMA, Object.class).getMappedResults().get(0);
		
		return new AggregationResult(field + capo.name(), (Integer) doc.get(field));
	}
	
	public Document listaDoppia () {
		Aggregation aggregation = Aggregation.newAggregation(Aggregation.sort(Sort.by(Direction.DESC, "dose2")));
		return template.aggregate(aggregation, TEMA, Object.class).getRawResults();
	}
	
	public Document listaDoppiaFiltra (String sigla) {
		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.match(Criteria.where("sigla").is(sigla)),
				Aggregation.sort(Sort.by(Direction.DESC, "dose2")));
		return template.aggregate(aggregation, TEMA, Object.class).getRawResults();
	}
}
