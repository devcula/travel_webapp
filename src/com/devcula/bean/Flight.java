package com.devcula.bean;

import java.util.Calendar;


public class Flight {
	public static final Double ECONOMY=4000D;
	public static final Double BUSINESS=10000D;
	public static final Double FIRST=20000D;

	private String source;
	private String destination;
	private Integer flightNumber;
	private String arrival;
	private String departure;
	//private String bookingData;
	private String flightName;
	private Calendar journeyDate;
	private String travelClass;
	private Integer quantity;
	private String bookingDate;
	private Double price;
	private String message;
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
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
	public Calendar getJourneyDate() {
		return journeyDate;
	}
	public void setJourneyDate(Calendar journeyDate) {
		this.journeyDate = journeyDate;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public String getTravelClass() {
		return travelClass;
	}
	public void setTravelClass(String travelClass) {
		this.travelClass = travelClass;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
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
	public Integer getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(Integer flightNumber) {
		this.flightNumber = flightNumber;
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
	/*public String getBookingData() {
		return bookingData;
	}
	public void setBookingData(String bookingData) {
		this.bookingData = bookingData;
	}*/
	public String getFlightName() {
		return flightName;
	}
	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}
	
	@Override
	public String toString(){
		return this.getFlightName()+" "+this.getFlightNumber();
	}

}
