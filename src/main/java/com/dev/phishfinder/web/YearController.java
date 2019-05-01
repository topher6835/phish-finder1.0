package com.dev.phishfinder.web;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.phishfinder.model.Years;
import com.dev.phishfinder.service.YearRepository;

@RestController
@RequestMapping("/api")
public class YearController {
	//private final Logger log = LoggerFactory.getLogger(YearController.class);
	
	@Autowired
	private YearRepository yearRepository;
	
	@GetMapping("/getYears")
	public ResponseEntity<Years> getYears() {	
		return yearRepository.getYears();	
	}
	
	@GetMapping("/evictYears")
	public void evictYears() {	
		yearRepository.reportCacheEvict();	
	}

}
