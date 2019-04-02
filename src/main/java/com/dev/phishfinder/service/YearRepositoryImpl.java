package com.dev.phishfinder.service;

import java.util.Date;
import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.dev.phishfinder.model.Year;

@Component
public class YearRepositoryImpl implements YearRepository {
	
	private String bearerToken = 
			"4c0c969e2f383ceb7b4dad242e5e8a210b30635c394867ebe5963c646ee3f7e5df53f0cda2a003e8238a28a211b820d5";
	private String phishinEndpointUrl = "https://phish.in/api/v1/";
	
	@Override
	@Cacheable("getYears")
	public List<String> getYears() {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setBearerAuth(bearerToken);
		HttpEntity<?> httpEntity = new HttpEntity<Object>(requestHeaders);
		
		ResponseEntity<Year> yearsList = 
				restTemplate.exchange(phishinEndpointUrl + "years", HttpMethod.GET, httpEntity, Year.class);
		
		return yearsList.getBody().getYears();
	}
	
	@Override
	@CacheEvict(allEntries = true, value = {"getYears"})
	public void reportCacheEvict() {
		System.out.println("Flush Years Cache " + new Date() );
	}
	
    private void simulateSlowService() {
        try {
            long time = 2000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

}
