package com.devcula.bean;

import java.util.Calendar;

public class Train {
	public static final Double SLEEPER=500D;
	public static final Double THIRDAC=2500D;
	public static final Double SECONDAC=5000D;
	public static final Double FIRSTAC=7500D;
	
	private String source;
	private String destination;
	private Integer trainNumber;
	private String arrival;
	private String departure;
	//private String bookingData;
	private String trainName;
	private Calendar journeyDate;
	private String travelClass;
	private Integer quantity;
	private String bookingDate;
	private Double price;
	private Integer availableSeats;
	
	
	public Integer getAvailableSeats() {
		return availableSeats;
	}
	public void setAvailableSeats(Integer availableSeats) {
		this.availableSeats = availableSeats;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public Integer getTrainNumber() {
		return trainNumber;
	}
	public void setTrainNumber(Integer trainNumber) {
		this.trainNumber = trainNumber;
	}
	public String getArrival() {
		return arrival;
	}
	public void setArrival(String arrival) {
		this.arrival = arrival;
	}
	public String getDeparture() {
		return departure;
	}
	public void setDeparture(String departure) {
		this.departure = departure;
	}
	public String getTrainName() {
		return trainName;
	}
	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}
	public Calendar getJourneyDate() {
		return journeyDate;
	}
	public void setJourneyDate(Calendar journeyDate) {
		this.journeyDate = journeyDate;
	}
	public String getTravelClass() {
		return travelClass;
	}
	public void setTravelClass(String travelClass) {
		this.travelClass = travelClass;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	
}
