package com.depa.progettinocovid.DataExtractor.rest;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.depa.progettinocovid.DataExtractor.context.ApplicationContextProvider;
import com.depa.progettinocovid.DataExtractor.exceptions.TemaAlreadyRunningException;

import commons.rest.Response;

@RestController
public class EstrazioneRestController {

	@Autowired
	private ApplicationContextProvider provider;
	
	@Autowired
	private ThreadPoolExecutor threadExecutor;
	
	@Autowired
	private ConcurrentHashMap<String, Thread> threadMap;
	
	@GetMapping(path = "/estrai/{tema}")
	public ResponseEntity<Response<Object>> estrai (@PathVariable String tema){
		
		if (threadMap.containsKey(tema)) {
			if (threadMap.get(tema).isInterrupted()) {
				threadMap.remove(tema);
			} else {
				throw new TemaAlreadyRunningException(tema);
			}
		}
		
		Thread controller = new Thread(new Runnable() {
			
			@Override
			public void run() {
				EstrazioneRunnable estrazione = new EstrazioneRunnable(provider.getApplicationContext());
				estrazione.setTema(tema);
				threadExecutor.execute(estrazione);
				try {
					Thread.sleep(5000);
					System.out.println("Thread interrupted: " + estrazione.getWorker().isInterrupted());
					estrazione.stop();
					System.out.println("Thread interrupted: " + estrazione.getWorker().isInterrupted());
				} catch (InterruptedException e) {
					return;
				}
			}
		});
		
		threadMap.put(tema, controller);
		
		controller.run();
		
		Response<Object> res = Response.<Object>builder()
				.type(Response.Type.SUCCESS)
				.code(HttpStatus.OK.value())
				.description("Request sent")
				.build();
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
}