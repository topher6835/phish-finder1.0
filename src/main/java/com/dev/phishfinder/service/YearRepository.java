package com.dev.phishfinder.service;

import org.springframework.http.ResponseEntity;

import com.dev.phishfinder.model.Years;

public interface YearRepository {

	ResponseEntity<Years> getYears();

	void reportCacheEvict();
	
}
