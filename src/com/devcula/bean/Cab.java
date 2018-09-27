package com.devcula.bean;

import java.util.Calendar;

public class Cab {
	public static final Double HATCHBACK=300D;
	public static final Double SEDAN=500D;
	public static final Double SUV=600D;
	
	private String city;
	private String source;
	private String destination;
	private Calendar journeyDate;
	private String carType;
	private String cabNumber;
	private String cabName;
	private String driverName;
	private String bookingDate;
	private Double price;
	private String message;
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
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
	public Calendar getJourneyDate() {
		return journeyDate;
	}
	public void setJourneyDate(Calendar journeyDate) {
		this.journeyDate = journeyDate;
	}
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	public String getCabNumber() {
		return cabNumber;
	}
	public void setCabNumber(String cabNumber) {
		this.cabNumber = cabNumber;
	}
	public String getCabName() {
		return cabName;
	}
	public void setCabName(String cabName) {
		this.cabName = cabName;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
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
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
