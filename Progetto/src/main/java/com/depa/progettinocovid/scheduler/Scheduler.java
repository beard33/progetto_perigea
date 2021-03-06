package com.depa.progettinocovid.scheduler;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.depa.progettinocovid.models.ConteggioDto;
import com.depa.progettinocovid.models.Processo;
import com.depa.progettinocovid.rest.VacciniRestClient;
import com.depa.progettinocovid.service.AssociazioneProvinceService;
import com.depa.progettinocovid.service.ConteggioService;
import com.depa.progettinocovid.service.KafkaService;
import com.depa.progettinocovid.service.ProcessoService;

@EnableScheduling
@Component
public class Scheduler {
	
	@Autowired
	private VacciniRestClient vacciniRestClient;
	
	@Autowired
	private KafkaService kafkaService;
	
	@Autowired
	private ProcessoService processoService;
	
	@Autowired
	private ConteggioService conteggioService;
	
	@Autowired
	private AssociazioneProvinceService provinceService;

//	ogni giorno
    @Scheduled(fixedRate = 86400000)
    public void prendiDati() {
    	
    	Processo processo = new Processo();
    	
//    	misuro quanto ci mette
    	processo.setInizio(new Date());
		List<ConteggioDto> conteggi = vacciniRestClient.prendiDati();
		processo.setFine(new Date());
		
//		TODO performance
		conteggi.stream().forEach(c->{
			conteggioService.addData(c);
			conteggioService.addSigla(c);
		});
		
		conteggi.stream().forEach(c->kafkaService.send(c));
		kafkaService.closeKafka();
		processoService.save(processo);
    }
    
//    ogni mese
    @Scheduled(cron = "0 0 0 20 * *")
    public void aggiornaProvince() {
    	provinceService.emptyDB();
    	provinceService.fillTable();
    }
}
