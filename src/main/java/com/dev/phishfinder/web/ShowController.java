package com.dev.phishfinder.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.phishfinder.model.ShowData;
import com.dev.phishfinder.model.Track;
import com.dev.phishfinder.service.ShowRepository;
import com.dev.phishfinder.service.TrackRepository;

@RestController
@RequestMapping("/api")
public class ShowController {
	private final Logger log = LoggerFactory.getLogger(ShowController.class);

	@Autowired
	private ShowRepository showRepository;
	@Autowired
	private TrackRepository trackRepository;
	
	@GetMapping("/show/year/{yearId}")
	public ResponseEntity<?> getShowsByYear(@PathVariable String yearId) {
		return showRepository.getShowsByYear(yearId);
	}
	
	@GetMapping("/show/id/{showId}")
	public ShowData getShowById(@PathVariable Long showId) {
		return showRepository.getShowById(showId);
	}
	
	@GetMapping("show/tracks/{showId}")
	public List<Track> getTracksByShowId(@PathVariable Long showId) {
		return trackRepository.getTracksByShowId(showId);
	}
	
	@GetMapping("/evictShows")
	public void evictYears() {
		log.info("evictShows()");
		showRepository.reportCacheEvict();	
	}

}
