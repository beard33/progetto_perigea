package com.depa.progettinocovid.DataExtractor.factory;

import com.depa.progettinocovid.DataExtractor.service.GenericService;

public interface AbstractFactory {
	
	public GenericService getService(String type);
}
