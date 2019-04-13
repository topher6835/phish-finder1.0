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
import com.dev.phishfinder.model.Shows;

@Component
public class ShowRepositoryImpl implements ShowRepository {

	@Autowired
	TrackRepository trackRepository;
	@Autowired 
	ShowRepository showRepository;
	
	private String bearerToken = 
			"4c0c969e2f383ceb7b4dad242e5e8a210b30635c394867ebe5963c646ee3f7e5df53f0cda2a003e8238a28a211b820d5";
	private String phishinEndpointUrl = "https://phish.in/api/v1/";

	@Override
	@Cacheable(cacheNames="shows", key="#yearId")
	public ResponseEntity<?> getShowsByYear(String yearId) {	//List<Show>
		System.out.println("getShowsByYear repo");
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setBearerAuth(bearerToken);
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
		
		return showsList;   // showsList.getBody().getShowsList();
	}
	
	@Override
	@Cacheable(cacheNames="show", key="#showId")
	public ShowData setShowById(Long showId, Show theShow) {
		System.out.println("/n--> setShow(" + showId + ", xx)");
		
		ShowData newShow = new ShowData(theShow);
		
		return newShow;
	}
	
//	@Override
//	@Cacheable(cacheNames="show", key="#showId")
//	public Show setShowById(Long showId, Show theShow) {
//		System.out.println("/n--> setShow(" + showId + ", xx)");
//		
//		Show newShow = new Show(theShow);
//		
//		return newShow;
//	}

	@Override
	@Cacheable(cacheNames="show", key="#showId")
	public ShowData getShowById(Long showId) {
		System.out.println("/n--> getShowById(" + showId + ")");
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setBearerAuth(bearerToken);
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


