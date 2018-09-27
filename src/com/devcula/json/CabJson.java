package com.devcula.json;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.devcula.resources.JSONParser;

public class CabJson {
	private String bookingDate;
	private Integer cabs;
	public String getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}
	public Integer getCabs() {
		return cabs;
	}
	public void setCabs(Integer cabs) {
		this.cabs = cabs;
	}
	
	public CabJson(String bookingDate){
		this.bookingDate=bookingDate;
		this.cabs=50;
	}
	
	public static String generateData(Integer months) throws Exception{
		String result=null;
		try{
			List<CabJson> list=new ArrayList<>();
			LocalDate today=LocalDate.now();
			for(long i=0; i<=30*months; i++){
				CabJson temp=new CabJson(Date.valueOf(today.plusDays(i)).toString());
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
