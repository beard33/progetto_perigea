package com.depa.progettinocovid.taskScheduler.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.depa.progettinocovid.taskScheduler.rest.ScheduledInfo;

@Repository
public interface ScheduledInfoRepository extends MongoRepository<ScheduledInfo, String>{
	
}
