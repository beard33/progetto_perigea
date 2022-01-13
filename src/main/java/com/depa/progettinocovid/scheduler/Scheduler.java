package com.depa.progettinocovid.scheduler;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.depa.progettinocovid.models.Conteggio;
import com.depa.progettinocovid.models.Processo;
import com.depa.progettinocovid.rest.VacciniRestClient;
import com.depa.progettinocovid.service.ConteggioService;
import com.depa.progettinocovid.service.ProcessoService;
import com.depa.progettinocovid.util.KafkaTransact;

@EnableScheduling
@Component
public class Scheduler {
	
	@Autowired
	private VacciniRestClient vacciniRestClient;
	
	@Autowired
	private KafkaTransact kafkaTransact;
	
	@Autowired
	private ProcessoService processoService;
	
	@Autowired
	private ConteggioService conteggioService;

//	ogni giorno
    @Scheduled(fixedRate = 86400000)
    public void prendiDati() {
    	
    	Processo processo = new Processo();
    	
//    	misuro quanto ci mette
    	processo.setInizio(new Date());
		List<Conteggio> conteggi = vacciniRestClient.prendiDati();
		processo.setFine(new Date());
		
		conteggi.stream().forEach(c->{
			conteggioService.addData(c);
			conteggioService.addSigla(c);
		});
		
		conteggi.stream().forEach(c->kafkaTransact.send(c));
		
		processoService.save(processo);
    }
}
