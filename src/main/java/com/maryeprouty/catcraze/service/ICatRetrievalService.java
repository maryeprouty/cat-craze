package com.maryeprouty.catcraze.service;

import org.springframework.stereotype.Component;

import com.maryeprouty.catcraze.model.Cat;

@Component
public interface ICatRetrievalService {
	
	public Cat getCat();

}
