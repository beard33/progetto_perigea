package com.depa.progettinocovid.DataAggregator.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AggregationResult {
	
	private String description;
	private int value;
}
