package com.depa.progettinocovid.DataExtractor.scheduler;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.depa.progettinocovid.DataExtractor.model.ConteggioDto;
import com.depa.progettinocovid.DataExtractor.model.Processo;
import com.depa.progettinocovid.DataExtractor.rest.VacciniRestClient;
import com.depa.progettinocovid.DataExtractor.service.ConteggioService;
import com.depa.progettinocovid.DataExtractor.service.KafkaService;
import com.depa.progettinocovid.DataExtractor.service.ProcessoService;
import com.depa.progettinocovid.DataExtractor.service.ProvinceService;

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
	private ProvinceService provinceService;
	
	
	@Scheduled(fixedRate = 86400000)
	public void getVaccinationData() {
		Processo processo = new Processo();
		
		processo.setInizio(new Date());
		List<ConteggioDto> conteggi = vacciniRestClient.getVaccinationData();
		processo.setFine(new Date());
		
		conteggi.stream().forEach(c -> {
			conteggioService.addDate(c);
			conteggioService.addSigla(c);
			kafkaService.send(c);
		});
		
		kafkaService.closeKafka();
		processoService.save(processo);
	}
	
	@Scheduled(cron = "0 0 0 20 * * ")
	public void updateProvince() {
		provinceService.emptyDB();
		provinceService.fillTable();
	}
	
}
