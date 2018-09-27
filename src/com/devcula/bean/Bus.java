package com.devcula.bean;

import java.util.Calendar;

public class Bus {
	public static final Double NONAC=100D;
	public static final Double AC=400D;
	
	private String source;
	private String destination;
	private Integer busNumber;
	private String arrival;
	private String departure;
	//private String bookingData;
	private String busName;
	private Calendar journeyDate;
	private String travelClass;
	private Integer quantity;
	private String bookingDate;
	private Double price;
	private Integer availableSeats;
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
	public Integer getBusNumber() {
		return busNumber;
	}
	public void setBusNumber(Integer busNumber) {
		this.busNumber = busNumber;
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
	public String getBusName() {
		return busName;
	}
	public void setBusName(String busName) {
		this.busName = busName;
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
	public Integer getAvailableSeats() {
		return availableSeats;
	}
	public void setAvailableSeats(Integer availableSeats) {
		this.availableSeats = availableSeats;
	}
	
}
