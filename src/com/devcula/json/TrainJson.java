package com.devcula.json;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.devcula.resources.JSONParser;

public class TrainJson {
	private String bookingDate;
	private Integer sleeper;
	private Integer thirdAC;
	private Integer secondAC;
	private Integer firstAC;
	
	public TrainJson(String bookingDate){
		this.bookingDate=bookingDate;
		this.sleeper=200;
		this.thirdAC=100;
		this.secondAC=25;
		this.firstAC=0;
	}

	public String getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}

	public Integer getSleeper() {
		return sleeper;
	}

	public void setSleeper(Integer sleeper) {
		this.sleeper = sleeper;
	}

	public Integer getThirdAC() {
		return thirdAC;
	}

	public void setThirdAC(Integer thirdAC) {
		this.thirdAC = thirdAC;
	}

	public Integer getSecondAC() {
		return secondAC;
	}

	public void setSecondAC(Integer secondAC) {
		this.secondAC = secondAC;
	}

	public Integer getFirstAC() {
		return firstAC;
	}

	public void setFirstAC(Integer firstAC) {
		this.firstAC = firstAC;
	}
	
	public static String generateData(Integer months) throws Exception{
		String result=null;
		try{
			List<TrainJson> list=new ArrayList<>();
			LocalDate today=LocalDate.now();
			for(long i=0; i<=30*months; i++){
				TrainJson temp=new TrainJson(Date.valueOf(today.plusDays(i)).toString());
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
