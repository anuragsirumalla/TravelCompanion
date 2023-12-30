package com.travelcompanion.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.travelcompanion.exceptions.DuplicateEmailException;
import com.travelcompanion.model.Passenger;
import com.travelcompanion.service.IEmailService;
import com.travelcompanion.service.IPassengerService;

@Controller
@RequestMapping("/travel")
public class TravelCompaignController {
	
	List<Passenger> passengers = new ArrayList<Passenger>();
	@Autowired
	private IEmailService emailService;
	@Autowired
	private IPassengerService passengerSerice;
	private final String SUCCESS_REGISTRATION = "SUCCESS";
	private final String SUCCESS_RESGISTRATION_MESSAGE = "REGISTERED SUCCESSFULLY with Travel Companion";
	
	
	@GetMapping("/passengers")
	public ResponseEntity<List<Passenger>> getAllPassengers(){
		List<Passenger> passengerDetails = passengerSerice.getAllPassengerDetails();
		return new ResponseEntity<List<Passenger>>(passengerDetails,HttpStatus.OK);
	}
	
	@GetMapping("/passenger/{passengerId}")
	public ResponseEntity<Passenger> getPassengerDetails(@PathVariable String passengerId){
		Passenger passenger = passengerSerice.getPassengerDetails(passengerId);
		try {
			if(passenger != null)
				return new ResponseEntity<Passenger>(passenger,HttpStatus.OK);
			else
				return new ResponseEntity<Passenger>(HttpStatus.NOT_FOUND);
		}catch(Exception ex) {
			return new ResponseEntity<Passenger>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/passenger-details")
	public ResponseEntity<Passenger> registerPassenger(@RequestBody Passenger passenger){
		
		try {
			Passenger newPassenger = passengerSerice.registerPassenger(passenger);
		  //emailService.sendEmail(passenger.getEmailId(),SUCCESS_RESGISTRATION_MESSAGE," Thanks for registering with Travel-Companion, Please go to app to find matchings for destinations");
			return new ResponseEntity<Passenger>(newPassenger, HttpStatus.OK);	
		}catch(DuplicateEmailException ex) {
			return new ResponseEntity<Passenger>(HttpStatus.BAD_REQUEST);
		}	
	}
	
	@PatchMapping("/passenger")
	public ResponseEntity<Passenger> updatePassengerDetails(@RequestBody Passenger passengerId){
		return new ResponseEntity<Passenger>(HttpStatus.OK);
	}
	
	@GetMapping("/find-matchings/{passengerId}")
	public ResponseEntity<List<Passenger>> getPassengerMatchings(@PathVariable String passengerId){
		List<Passenger> passengers = passengerSerice.getPassengerMatchings(passengerId);
		return new ResponseEntity<List<Passenger>>(passengers,HttpStatus.OK);
	}
	
	
	@PostMapping("/send-email")
	public ResponseEntity<String> sendEmailToPassengers(){
		try {
			emailService.sendEmail("anurags.0830@gmail.com", "Travel-companion Found", "Travel companion for Flight QR217 is Rakesh, you can contact him @ +18063192823");
			return new ResponseEntity<String>("Email sent successfully", HttpStatus.OK);
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			return new ResponseEntity<String>("Error while sending the mail",HttpStatus.BAD_REQUEST);
		}
	}

}
