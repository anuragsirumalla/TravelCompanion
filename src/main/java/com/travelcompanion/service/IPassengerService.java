package com.travelcompanion.service;

import java.util.List;
import java.util.Optional;

import com.travelcompanion.exceptions.DuplicateEmailException;
import com.travelcompanion.exceptions.TravelCompanionExceptions;
import com.travelcompanion.model.Passenger;

public interface IPassengerService {
	
	public List<Passenger> getAllPassengerDetails();
	public Passenger getPassengerDetails(String passengerId);
	public Passenger registerPassenger(Passenger passenger) throws DuplicateEmailException;
	public List<Passenger> getPassengerMatchings(String passengerId);

}
