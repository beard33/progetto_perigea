package com.depa.progettinocovid.DataExtractor.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.depa.progettinocovid.DataExtractor.mapper.ProcessoMapper;
import com.depa.progettinocovid.DataExtractor.model.Processo;
import com.depa.progettinocovid.DataExtractor.model.ProcessoDto;
import com.depa.progettinocovid.DataExtractor.repository.ProcessoRepository;

@Service
public class ProcessoService {

	@Autowired
	private ProcessoRepository repository;
	
	public void save(Processo p) {
		repository.save(p);
	}
	
	public List<ProcessoDto> findAll(){
		return ProcessoMapper.INSTANCE.mapToDtoList(repository.findAll());
	}
	
	public List<ProcessoDto> findByDate(Date date){
		List<Processo> processes = repository.findByInizioBetween(date, new Date(date.getTime() + 1000 * 60 * 60 * 24));
		return ProcessoMapper.INSTANCE.mapToDtoList(processes);
	}
	
}
