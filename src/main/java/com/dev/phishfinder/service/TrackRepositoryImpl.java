package com.dev.phishfinder.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.dev.phishfinder.model.Show;
import com.dev.phishfinder.model.ShowData;
import com.dev.phishfinder.model.Track;
import com.dev.phishfinder.model.TrackData;
import com.dev.phishfinder.model.Tracks;

@Component
public class TrackRepositoryImpl implements TrackRepository {
	
	@Autowired
	ShowRepository showRepository;
	
	private String bearerToken = 
			"4c0c969e2f383ceb7b4dad242e5e8a210b30635c394867ebe5963c646ee3f7e5df53f0cda2a003e8238a28a211b820d5";
	private String phishinEndpointUrl = "https://phish.in/api/v1/";

	@Override
	@Cacheable(cacheNames="track", key="#trackId")
	public Track getTrackById(Long trackId) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setBearerAuth(bearerToken);
		HttpEntity<?> httpEntity = new HttpEntity<Object>(requestHeaders);
		ResponseEntity<TrackData> trackData = 
				restTemplate.exchange(phishinEndpointUrl + "tracks/" + trackId, 
						HttpMethod.GET, httpEntity, TrackData.class);
		Track theTrack = trackData.getBody().getTrack();
		return theTrack;
	}

	@Override
	@Cacheable(cacheNames="tracks", key="#tracksShowId")
	public List<Track> setTracksByShow(Long tracksShowId, List<Track> trackList) {
		Tracks showTracks = new Tracks(tracksShowId, trackList);
		return showTracks.getTracks();
	}
	
	@Override
	@Cacheable(cacheNames="tracks", key="#tracksShowId")
	public List<Track> getTracksByShowId(Long tracksShowId) {
		System.out.println("/n--> getTracksByShow(" +  tracksShowId +")");
		
		// find show and return tracks
		ShowData theShow = showRepository.getShowById(tracksShowId);
		
		return theShow.getShow().getTracks();
	}
	
	@Override
	@CacheEvict(allEntries = true, value = {"track", "tracks"})
	public void reportCacheEvict() {
		System.out.println("Flush Track and Tracks Cache " + new Date() );
	}

}
