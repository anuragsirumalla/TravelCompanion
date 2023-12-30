package com.travelcompanion.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.travelcompanion.model.Passenger;

public interface IPassengerDetailsRepository extends MongoRepository<Passenger, String>{
	
	 public Passenger findByEmailId(String emailId);
}
