package com.depa.progettinocovid.taskScheduler.rest;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document
public class ScheduledInfo {
	
	public enum Tipo {PERIODICO, UNA_TANTUM}
	
	@Field
	@Id
	private String id;
	
	@Field
	private Date nextFireTime;
	
	@Field
	private String tema;
	
	@Field
	private String tipo;
	
	@Field
	private String cron;
}
