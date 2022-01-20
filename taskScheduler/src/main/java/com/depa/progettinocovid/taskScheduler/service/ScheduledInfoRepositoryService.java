package com.depa.progettinocovid.taskScheduler.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.depa.progettinocovid.taskScheduler.repository.ScheduledInfoRepository;
import com.depa.progettinocovid.taskScheduler.rest.ScheduledInfo;


@Service
public class ScheduledInfoRepositoryService {
	
	@Autowired
	private ScheduledInfoRepository repo;
	
	public List<ScheduledInfo> getAll() {
		return repo.findAll();
	}
	
	public void deleteJobById(String id) {
		repo.deleteById(id);
	}
	
	public void deleteAll() {
		repo.deleteAll();
	}
	
	public void save(ScheduledInfo info) {
		repo.save(info);
	}
}
