package com.dev.phishfinder.service;

import java.util.Date;
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

import com.dev.phishfinder.model.Show;
import com.dev.phishfinder.model.ShowData;
import com.dev.phishfinder.model.Shows;

@Component
public class ShowRepositoryImpl implements ShowRepository {

	@Autowired
	TrackRepository trackRepository;
	@Autowired 
	ShowRepository showRepository;
	
	@Value("${bearerToken}")
	private String bearerToken;
	@Value("${phishinEndpoint}")
	private String phishinEndpointUrl;

	@Override
	@Cacheable(cacheNames="shows", key="#yearId")
	public ResponseEntity<?> getShowsByYear(String yearId) {
		System.out.println("getShowsByYear repo");
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setBearerAuth(bearerToken);
		requestHeaders.add("Content-Type", "application/json");
		requestHeaders.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<?> httpEntity = new HttpEntity<Object>(requestHeaders);
		
		ResponseEntity<Shows> showsList = 
				restTemplate.exchange(phishinEndpointUrl + "years/" + yearId, 
						HttpMethod.GET, httpEntity, Shows.class);	
		showsList.getBody().setYearId(yearId);
		for(Show theShow : showsList.getBody().getShowsList()) {
			theShow.setYearId(yearId);
			showRepository.setShowById(theShow.getId(), theShow);	
			trackRepository.setTracksByShow(theShow.getId(), theShow.getTracks());
		}
		
		return showsList;
	}
	
	@Override
	@Cacheable(cacheNames="show", key="#showId")
	public ShowData setShowById(Long showId, Show theShow) {
		System.out.println("--> setShow(" + showId + ")");
		
		ShowData newShow = new ShowData(theShow);
		
		return newShow;
	}

	@Override
	@Cacheable(cacheNames="show", key="#showId")
	public ShowData getShowById(Long showId) {
		System.out.println("--> getShowById(" + showId + ")");
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setBearerAuth(bearerToken);
		requestHeaders.add("Content-Type", "application/json");
		requestHeaders.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<?> httpEntity = new HttpEntity<Object>(requestHeaders);
		
		ResponseEntity<ShowData> show = 
				restTemplate.exchange(phishinEndpointUrl + "shows/" + showId, 
						HttpMethod.GET, httpEntity, ShowData.class);
		Show theShow = show.getBody().getShow();

		trackRepository.setTracksByShow(showId, theShow.getTracks());
		
		return show.getBody();
	}

	@Override
	@CacheEvict(allEntries = true, value = {"shows", "show"})
	public void reportCacheEvict() {
		System.out.println("Flush Shows and Show Cache " + new Date() );
	}

}


