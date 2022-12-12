package com.maryeprouty.catcraze.service;

import org.springframework.web.client.RestTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.maryeprouty.catcraze.model.Cat;

@Component
public class CatRetrievalService implements ICatRetrievalService {
	
	private String catUri = "https://api.thecatapi.com/v1/images/search?api_key={api-key}&has_breeds=1";
	
	@Autowired
	private Environment env;

	@Override
	public Cat getCat() {
		
		RestTemplate restTemplate = new RestTemplate();
		String apiKey = env.getProperty("catApiKey");

		Cat cat = null;
		
		try {
			
			ResponseEntity<Cat[]> catResponse = restTemplate.getForEntity(catUri, Cat[].class, apiKey);
			Cat[] cats = catResponse.getBody();
			cat = cats[0];
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			
		}
		
		return cat;
	}

}
