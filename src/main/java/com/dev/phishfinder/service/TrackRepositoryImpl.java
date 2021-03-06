package com.dev.phishfinder.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.dev.phishfinder.model.ShowData;
import com.dev.phishfinder.model.Track;
import com.dev.phishfinder.model.TrackData;
import com.dev.phishfinder.model.Tracks;

@Component
public class TrackRepositoryImpl implements TrackRepository {
	
	@Autowired
	ShowRepository showRepository;
	
	@Value("${bearerToken}")
	private String bearerToken;
	@Value("${phishinEndpoint}")
	private String phishinEndpointUrl;

	@Override
	@Cacheable(cacheNames="track", key="#trackId")
	public Track getTrackById(Long trackId) {
		System.out.println("getTrackById(" +  trackId +")");
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setBearerAuth(bearerToken);
		requestHeaders.add("Content-Type", "application/json");
		requestHeaders.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
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
		System.out.println("getTracksByShow(" +  tracksShowId +")");
		
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
