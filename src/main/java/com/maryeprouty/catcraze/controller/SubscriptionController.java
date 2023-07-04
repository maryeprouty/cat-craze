package com.maryeprouty.catcraze.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.maryeprouty.catcraze.model.SubscriberPhoneNumber;
import com.maryeprouty.catcraze.repository.PhoneNumberRepository;

@RestController
public class SubscriptionController {
	
	@Autowired
	private PhoneNumberRepository phoneRepo;
	
	@PostMapping(path = "/subscribe")
	public ResponseEntity<String> subscribe(@RequestBody SubscriberPhoneNumber subscriberNumber) {
		try {
			
			if (subscriberNumber != null && subscriberNumber.getPhoneNumber() != null) {
				
				if (!phoneRepo.existsByPhoneNumber(subscriberNumber.getPhoneNumber())) {
					
					phoneRepo.save(subscriberNumber);
					
					return new ResponseEntity<String>("Subscribed!", HttpStatus.CREATED);
					
				} else {
					
					return new ResponseEntity<String>("You've already subscribed.", HttpStatus.OK);
					
				}
			} else {
				
				return new ResponseEntity<String>("Please provide a valid phone number.", HttpStatus.BAD_REQUEST);
				
			}
			
			
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			
			return new ResponseEntity<String>("An error occurred while trying to subscribe.", 
				HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PutMapping(path = "unsubscribe")
	public ResponseEntity<String> unsubscribe(@RequestBody SubscriberPhoneNumber subscriberNumber) {
		try {
			
			if (subscriberNumber != null && subscriberNumber.getPhoneNumber() != null) {
				
				phoneRepo.deleteByPhoneNumber(subscriberNumber.getPhoneNumber());
				
				return new ResponseEntity<String>("Unsubscribed.", HttpStatus.OK);
				
			} else {
				
				return new ResponseEntity<String>("Please provide a valid phone number.", HttpStatus.BAD_REQUEST);
				
			}
					
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			
			return new ResponseEntity<String>("An error occurred while trying to unsubscribe.", 
				HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
