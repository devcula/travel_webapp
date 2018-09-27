package com.devcula.bean;

import java.util.Calendar;

public class Hotel {
	public static final Double STANDARD=1000D;
	public static final Double DELUXE=5000D;
	
	private Integer hotelId;
	private String city;
	private String hotelName;
	private Double rating;
	private Double timesRated;
	private String roomsType;
	private Calendar checkInDate;
	private Calendar checkOutDate;
	private Double price;
	private String message;
	private Integer quantity;
	private String bookingDate;
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
	public String getRoomsType() {
		return roomsType;
	}
	public void setRoomsType(String roomsType) {
		this.roomsType = roomsType;
	}
	public Calendar getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(Calendar checkInDate) {
		this.checkInDate = checkInDate;
	}
	public Calendar getCheckOutDate() {
		return checkOutDate;
	}
	public void setCheckOutDate(Calendar checkOutDate) {
		this.checkOutDate = checkOutDate;
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
}
