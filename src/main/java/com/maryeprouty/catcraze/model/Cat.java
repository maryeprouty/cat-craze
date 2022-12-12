package com.maryeprouty.catcraze.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Cat {
	
	private String url;
	
	private Breed[] breeds;
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

	public Breed[] getBreeds() {
		return breeds;
	}

	public void setBreeds(Breed[] breeds) {
		this.breeds = breeds;
	}

}
