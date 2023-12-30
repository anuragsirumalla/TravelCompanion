package com.travelcompanion.model;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class PassengerDetails {

	@Id
	private Integer passengerId;
	private String firstName;
	private String lastName;
	private String destination;
	private String flightNumber;
	private Date dateOfTravel;
	private Double phoneNumber;
	
	public Integer getPassengerId() {
		return passengerId;
	}
	public void setPassengerId(Integer passengerId) {
		this.passengerId = passengerId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public Date getDateOfTravel() {
		return dateOfTravel;
	}
	public void setDateOfTravel(Date dateOfTravel) {
		this.dateOfTravel = dateOfTravel;
	}
	public Double getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(Double phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}
