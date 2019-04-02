package com.dev.phishfinder.service;

import java.util.List;

import com.dev.phishfinder.model.Show;

public interface ShowRepository {

	public List<Show> getShowsByYear(String yearId);
	
	public Show setShowById(Long showId, Show theShow);
	
	public Show getShowById(Long showId);

	public void reportCacheEvict();

}
