package com.devcula.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="bus")
public class BusEntity {
	private String source;
	private String destination;
	
	@Id
	private Integer busNumber;
	private String busType;
	private String arrival;
	private String departure;
	private String bookingData;
	private String busName;
	
	
	public String getBusType() {
		return busType;
	}
	public void setBusType(String busType) {
		this.busType = busType;
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
	public String getBookingData() {
		return bookingData;
	}
	public void setBookingData(String bookingData) {
		this.bookingData = bookingData;
	}
	public String getBusName() {
		return busName;
	}
	public void setBusName(String busName) {
		this.busName = busName;
	}
	
	
}
