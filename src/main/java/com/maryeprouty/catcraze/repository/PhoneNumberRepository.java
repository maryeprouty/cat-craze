package com.maryeprouty.catcraze.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.maryeprouty.catcraze.model.SubscriberPhoneNumber;

public interface PhoneNumberRepository extends CrudRepository<SubscriberPhoneNumber, Long> {
	
	@Override
	public List<SubscriberPhoneNumber> findAll();
	
	@Transactional
	public Integer deleteByPhoneNumber(String phoneNumber);
	
	public boolean existsByPhoneNumber(String phoneNumber);

}
