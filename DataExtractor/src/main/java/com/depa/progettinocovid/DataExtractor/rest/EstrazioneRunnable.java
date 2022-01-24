package com.depa.progettinocovid.DataExtractor.rest;

import java.util.Date;

import org.springframework.context.ApplicationContext;

import com.depa.progettinocovid.DataExtractor.exceptions.BadTemaRequestException;
import com.depa.progettinocovid.DataExtractor.factory.ServiceFactory;
import com.depa.progettinocovid.DataExtractor.model.Processo;
import com.depa.progettinocovid.DataExtractor.service.GenericService;
import com.depa.progettinocovid.DataExtractor.service.ProcessoService;

import commons.model.TemaEnum;
import lombok.Data;


@Data
public class EstrazioneRunnable implements Runnable {

	// Via ApplicationContext
	private ProcessoService processoService;
	private ServiceFactory factory;

	private String tema;
	private Thread worker;

	public EstrazioneRunnable(ApplicationContext context) {
		this.processoService = context.getBean(ProcessoService.class);
		this.factory = context.getBean(ServiceFactory.class);
		this.worker = Thread.currentThread();
	}
	
	@Override
	public void run() {
		Processo processo = new Processo();
		processo.setTipo(tema);
		processo.setInizio(new Date());
		TemaEnum temaPassato;
		try {
			temaPassato = TemaEnum.valueOf(tema.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new BadTemaRequestException();
		}

		GenericService service = factory.getService(temaPassato);
		service.prendiDati();
		service.pubblicaDati();
		processo.setFine(new Date());
		
		processoService.save(processo);
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			return;
		}
	}
	
	public void stop() {
		worker.interrupt();
	}
}
