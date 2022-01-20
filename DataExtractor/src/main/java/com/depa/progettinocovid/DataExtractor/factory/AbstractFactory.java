package com.depa.progettinocovid.DataExtractor.factory;

import com.depa.progettinocovid.DataExtractor.service.GenericService;

import commons.model.TemaEnum;

public interface AbstractFactory {
	
	public GenericService getService(TemaEnum type);
}
