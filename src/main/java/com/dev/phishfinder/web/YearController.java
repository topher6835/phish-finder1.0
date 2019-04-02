package com.dev.phishfinder.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.phishfinder.service.YearRepository;

@RestController
@RequestMapping("/api")
public class YearController {
	
	private final Logger log = LoggerFactory.getLogger(YearController.class);
	
	@Autowired
	private YearRepository yearRepository;
	
	@GetMapping("/getYears")
	List<String> getYears() {	
		return yearRepository.getYears();	
	}
	
	@GetMapping("/evictYears")
	void evictYears() {	
		yearRepository.reportCacheEvict();	
	}

}
