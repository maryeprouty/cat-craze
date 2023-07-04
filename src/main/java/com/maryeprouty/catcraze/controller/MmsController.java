package com.maryeprouty.catcraze.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maryeprouty.catcraze.model.Breed;
import com.maryeprouty.catcraze.model.Cat;
import com.maryeprouty.catcraze.model.SubscriberPhoneNumber;
import com.maryeprouty.catcraze.repository.PhoneNumberRepository;
import com.maryeprouty.catcraze.service.ICatRetrievalService;
import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

@RestController
public class MmsController {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private ICatRetrievalService catService;
	
	@Autowired
	private PhoneNumberRepository phoneRepo;
	
	@GetMapping(value = "/sendMms")
	public ResponseEntity<String> sendMms() {
		
		// TODO: Add more specific error handling and a logger.
		try {
			
			Twilio.init(env.getProperty("twilioSid"), env.getProperty("twilioAuthToken"));
			
			// Retrieve cat image and breed information from The Cat API and send text message
			Cat cat = catService.getCat();
			Breed breed = cat.getBreeds()[0]; // null checks
			
			// For each subscriber phone number, send a text message
			List<SubscriberPhoneNumber> subscriberList = phoneRepo.findAll();
			
			// messagingService.sendMms(cat, subscribers)
			
			for (SubscriberPhoneNumber subscriber: subscriberList) {
				
				if (subscriber != null && subscriber.getPhoneNumber() != null) {
					
					String recipientNumber = subscriber.getPhoneNumber();
					
					try {
						
						Message.creator(
								new PhoneNumber(recipientNumber),
				                new PhoneNumber(env.getProperty("twilioPhoneNumber")), 
				                breed.getDescription())
						.setMediaUrl(
				                Arrays.asList(URI.create(cat.getUrl())))
						.create();
						
					} catch (ApiException e) {
						
						System.out.println(e.getMessage());
						
					}
				} else {
					
					System.out.println("Not able to send a message to a null subscriber.");
				}							
			}
			
			return new ResponseEntity<String>("Messages sent successfully.", HttpStatus.OK);
			
		} catch(Exception e) {
			
			System.out.println(e.getMessage());
			
			return new ResponseEntity<String>("An error has occurred and the messages were not able to send.", 
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}

}
