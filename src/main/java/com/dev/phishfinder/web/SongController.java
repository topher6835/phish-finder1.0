package com.dev.phishfinder.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.phishfinder.model.SongData;
import com.dev.phishfinder.service.SongRepository;

@RestController
@RequestMapping("/api")
public class SongController {
	private final Logger log = LoggerFactory.getLogger(SongController.class);
	
	@Autowired
	private SongRepository songRepository;

	@GetMapping("/song/id/{songId}")
	public SongData getSongById(@PathVariable Long songId) {
		return songRepository.getSongById(songId);
	}
	
	@GetMapping("/evictSongs")
	public void evictSongs() {
		log.info("evictSongs()");
		songRepository.reportCacheEvict();
	}

}
