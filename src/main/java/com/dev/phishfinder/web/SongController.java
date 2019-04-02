package com.dev.phishfinder.web;

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
	
	@Autowired
	private SongRepository songRepository;

	@GetMapping("/song/id/{songId}")
	public SongData getSongById(@PathVariable Long songId) {
		return songRepository.getSongById(songId);
	}

}
