package com.devcula.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="hotel")
public class HotelEntity {
	@Id
	private Integer hotelId;
	private String city;
	private String hotelName;
	private String bookingData;
	private Double rating;
	private Double timesRated;
	private String roomsType;
	
	public String getRoomsType() {
		return roomsType;
	}
	public void setRoomsType(String roomsType) {
		this.roomsType = roomsType;
	}
	public Integer getHotelId() {
		return hotelId;
	}
	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getBookingData() {
		return bookingData;
	}
	public void setBookingData(String bookingData) {
		this.bookingData = bookingData;
	}
	public Double getRating() {
		return rating;
	}
	public void setRating(Double rating) {
		this.rating = rating;
	}
	public Double getTimesRated() {
		return timesRated;
	}
	public void setTimesRated(Double timesRated) {
		this.timesRated = timesRated;
	}
	
}
