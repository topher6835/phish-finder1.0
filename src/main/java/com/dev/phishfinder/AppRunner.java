package com.dev.phishfinder;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.dev.phishfinder.service.ShowRepository;
import com.dev.phishfinder.service.SongRepository;
import com.dev.phishfinder.service.TrackRepository;
import com.dev.phishfinder.service.YearRepository;

@Component
public class AppRunner implements CommandLineRunner {
	
	//private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

	private final YearRepository yearRepository;
	private final ShowRepository showRepository;
	private final SongRepository songRepository;
	private final TrackRepository trackRepository;

    public AppRunner(YearRepository yearRepository, ShowRepository showRepository, 
    		SongRepository songRepository, TrackRepository trackRepository) {
        this.yearRepository = yearRepository;
        this.showRepository = showRepository;
        this.songRepository = songRepository;
        this.trackRepository = trackRepository;
    }

	@Override
	public void run(String... args) throws Exception {
	}

}
