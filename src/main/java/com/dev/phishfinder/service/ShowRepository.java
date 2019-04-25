package com.dev.phishfinder.service;

import org.springframework.http.ResponseEntity;

import com.dev.phishfinder.model.Show;
import com.dev.phishfinder.model.ShowData;

public interface ShowRepository {

	public ResponseEntity<?> getShowsByYear(String yearId);
	
	public ShowData setShowById(Long showId, Show theShow);
	
	public ShowData getShowById(Long showId);

	public void reportCacheEvict();

}
