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

import com.dev.phishfinder.model.Years;

@Component
public class YearRepositoryImpl implements YearRepository {
	
	@Value("${bearerToken}")
	private String bearerToken;
	@Value("${phishinEndpoint}")
	private String phishinEndpointUrl;
	
	@Override
	@Cacheable("getYears")
	public ResponseEntity<Years> getYears() {
		System.out.println("getYears repo");
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setBearerAuth(bearerToken);
		requestHeaders.add("Content-Type", "application/json");
		requestHeaders.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<?> httpEntity = new HttpEntity<Object>(requestHeaders);
		
		ResponseEntity<Years> yearsList = 
				restTemplate.exchange(phishinEndpointUrl + "years", HttpMethod.GET, httpEntity, Years.class);	

		//yearsList.getBody().setStatusCode(yearsList.getStatusCodeValue());
		return yearsList; //yearsList.getBody();
	}
	
	@Override
	@CacheEvict(allEntries = true, value = {"getYears"})
	public void reportCacheEvict() {
		System.out.println("Flush Years Cache " + new Date() );
	}
	
	public void testEvict() {
		System.out.println("test evict.");
	}
	
//    private void simulateSlowService() {
//        try {
//            long time = 2000L;
//            Thread.sleep(time);
//        } catch (InterruptedException e) {
//            throw new IllegalStateException(e);
//        }
//    }

}
