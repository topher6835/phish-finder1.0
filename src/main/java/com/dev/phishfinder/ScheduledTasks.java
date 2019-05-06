package com.dev.phishfinder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.dev.phishfinder.web.ShowController;
import com.dev.phishfinder.web.SongController;
import com.dev.phishfinder.web.YearController;

@Component
public class ScheduledTasks {
	private final Logger log = LoggerFactory.getLogger(ShowController.class);
	
	@Autowired
	private YearController yearController;
	@Autowired
	private ShowController showController;
	@Autowired
	private SongController songController;
	
	@Scheduled(cron = "0 0 6 * * ?")
	public void evictCache() {
		System.out.println("Running scheduled cache flush...");
		log.info("Running scheduled cache flush");
		yearController.evictYears();
		showController.evictYears();
		songController.evictSongs();
	}

}
