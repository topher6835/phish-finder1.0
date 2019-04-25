package com.dev.phishfinder.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
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

	@Value("${bearerToken}")
	private String bearerToken;
	@Value("${phishinEndpoint}")
	private String phishinEndpointUrl;
	
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
