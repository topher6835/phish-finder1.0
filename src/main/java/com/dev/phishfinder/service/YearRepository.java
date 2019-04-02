package com.dev.phishfinder.service;

import java.util.List;

public interface YearRepository {

	List<String> getYears();

	void reportCacheEvict();
	
}
