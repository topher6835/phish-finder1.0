package com.dev.phishfinder.service;

import com.dev.phishfinder.model.SongData;

public interface SongRepository {

	public SongData getSongById(Long id);

	public void reportCacheEvict();

}
