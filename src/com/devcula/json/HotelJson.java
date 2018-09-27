package com.devcula.json;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.devcula.resources.JSONParser;

public class HotelJson {
	private String bookingDate;
	private Integer rooms;
	
	public HotelJson(String bookingDate){
		this.bookingDate=bookingDate;
		this.rooms=50;
	}
	public String getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}
	public Integer getRooms() {
		return rooms;
	}
	public void setRooms(Integer rooms) {
		this.rooms = rooms;
	}
	
	public static String generateData(Integer months) throws Exception{
		String result=null;
		try{
			List<HotelJson> list=new ArrayList<>();
			LocalDate today=LocalDate.now();
			for(long i=0; i<=30*months; i++){
				HotelJson temp=new HotelJson(Date.valueOf(today.plusDays(i)).toString());
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
