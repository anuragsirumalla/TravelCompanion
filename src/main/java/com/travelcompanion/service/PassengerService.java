package com.travelcompanion.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travelcompanion.exceptions.DuplicateEmailException;
import com.travelcompanion.exceptions.TravelCompanionExceptions;
import com.travelcompanion.model.Passenger;
import com.travelcompanion.repository.IPassengerDetailsRepository;

@Service
public class PassengerService implements IPassengerService{
	
	@Autowired
	private IPassengerDetailsRepository passengerRepository;

	@Override
	public List<Passenger> getAllPassengerDetails(){
			return passengerRepository.findAll();	
	}
	
	@Override
	public Passenger getPassengerDetails(String passengerId){
		return passengerRepository.findById(passengerId).get();
		
	}
	
	@Override
	public Passenger registerPassenger(Passenger passenger) throws DuplicateEmailException {
		try {
			if (isEmailUnique(passenger.getEmailId())) {
				System.out.println("Saving Data");
				passengerRepository.save(passenger);
	        } else {
	        	throw new DuplicateEmailException("Email Registered ALready !! ");
	        }
	    }catch(Exception ex) {
	    	System.out.println(ex.getMessage());
	    }
		return passenger;
	}
	
	@Override
	public List<Passenger> getPassengerMatchings(String passengerId) {
		Passenger passengerLookingForMatches = passengerRepository.findById(passengerId).get();
		List<Passenger> passengers = passengerRepository.findAll();	

		List<String> passengerIntrestsLookingForMatches = passengerLookingForMatches.getIntrests();
		List<Passenger> likesForSpecificPassenger = passengers.stream()
                .filter(eachPassenger -> (eachPassenger != passengerLookingForMatches && eachPassenger.getIntrests().retainAll(passengerIntrestsLookingForMatches)))
                .collect(Collectors.toList());
		System.out.println("likesForSpecificPassenger :: ");
		likesForSpecificPassenger.forEach(System.out::println);
			
		
		//Double matchingPercentage = calculateMatchingPercentage(likesForSpecificPassenger, disLikesForSpecificPassenger);
		return likesForSpecificPassenger;
	}
	
	private boolean isEmailUnique(String email) {
		System.out.println(email);
		Passenger existingUser = passengerRepository.findByEmailId(email);
	    return existingUser == null;
	}

//	private Double calculateMatchingPercentage(List<Passenger> likesCount, List<Passenger> disLikesCount) {
//		
//		System.out.println("List of Matching Passenegr Likes :");
//		likesCount.forEach(System.out::println);
//		
//		System.out.println("List of Matching Passenegr DisLikes :");
//		disLikesCount.forEach(System.out::println);
//		return 0.0;
//	}
	

}
