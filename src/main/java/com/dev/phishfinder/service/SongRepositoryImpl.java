package com.dev.phishfinder.service;

import java.util.Date;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.dev.phishfinder.model.SongData;

@Component
public class SongRepositoryImpl implements SongRepository {

	private String bearerToken = 
			"4c0c969e2f383ceb7b4dad242e5e8a210b30635c394867ebe5963c646ee3f7e5df53f0cda2a003e8238a28a211b820d5";
	private String phishinEndpointUrl = "https://phish.in/api/v1/";
	
	@Override
	@Cacheable(cacheNames="song", key="#songId")
	public SongData getSongById(Long songId) {
		System.out.println("getSongById repo");
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setBearerAuth(bearerToken);
		HttpEntity<?> httpEntity = new HttpEntity<Object>(requestHeaders);
		
		ResponseEntity<SongData> song = 
				restTemplate.exchange(phishinEndpointUrl + "songs/" + songId, 
						HttpMethod.GET, httpEntity, SongData.class);
		
		return song.getBody();
	}
	
	@Override
	@CacheEvict(allEntries = true, value = {"song"})
	public void reportCacheEvict() {
		System.out.println("Flush Song Cache " + new Date() );
	}

}
