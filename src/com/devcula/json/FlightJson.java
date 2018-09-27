package com.devcula.json;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.devcula.resources.JSONParser;

public class FlightJson {
	private String bookingDate;
	private Integer economy;
	private Integer businessClass;
	private Integer firstClass;
	
	public FlightJson(String bookingDate){
		this.bookingDate=bookingDate;
		this.economy=200;
		this.businessClass=100;
		this.firstClass=25;
	}

	public String getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}

	public Integer getEconomy() {
		return economy;
	}

	public void setEconomy(Integer economy) {
		this.economy = economy;
	}

	public Integer getBusinessClass() {
		return businessClass;
	}

	public void setBusinessClass(Integer businessClass) {
		this.businessClass = businessClass;
	}

	public Integer getFirstClass() {
		return firstClass;
	}

	public void setFirstClass(Integer firstClass) {
		this.firstClass = firstClass;
	}
	
	public static String generateData(Integer months) throws Exception{
		String result=null;
		try{
			List<FlightJson> list=new ArrayList<>();
			LocalDate today=LocalDate.now();
			for(long i=0; i<=30*months; i++){
				FlightJson temp=new FlightJson(Date.valueOf(today.plusDays(i)).toString());
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
