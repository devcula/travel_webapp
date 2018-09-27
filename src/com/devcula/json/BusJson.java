package com.devcula.json;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.devcula.resources.JSONParser;

public class BusJson {
	private String bookingDate;
	private Integer seats;
	
	public BusJson(String bookingDate){
		this.bookingDate=bookingDate;
		this.seats=50;
	}

	public String getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}

	public Integer getSeats() {
		return seats;
	}

	public void setSeats(Integer seats) {
		this.seats = seats;
	}
	
	public static String generateData(Integer months) throws Exception{
		String result=null;
		try{
			List<BusJson> list=new ArrayList<>();
			LocalDate today=LocalDate.now();
			for(long i=0; i<=30*months; i++){
				BusJson temp=new BusJson(Date.valueOf(today.plusDays(i)).toString());
				list.add(temp);
			}
			result= JSONParser.toJson(list);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
}
