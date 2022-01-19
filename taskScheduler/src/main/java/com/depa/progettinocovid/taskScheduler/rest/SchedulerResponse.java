package com.depa.progettinocovid.taskScheduler.rest;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SchedulerResponse {

	private String id;
	private Date data;
	private String tema;
	
	
}
