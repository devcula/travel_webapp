package com.devcula.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.lang.reflect.Type;

import com.devcula.bean.Flight;
import com.devcula.json.FlightJson;
import com.devcula.resources.Factory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Calendar;

import org.apache.log4j.Logger;

public class FlightService{
  public List<Flight> checkAvailability(Flight flight) throws Exception{
    try{
      return Factory.createFlightDAO().checkAvailability(flight);
    }
    catch(Exception e){
      Logger logger=Logger.getLogger(this.getClass());
      logger.error(e.getMessage(),e);
      throw e;
    }
  }
  
  public FlightJson filterBookingData(Calendar journeyDate, String inputData) throws Exception{
	  try{
			Gson gson=new Gson();
			Type type=new TypeToken<List<FlightJson>>() {}.getType();
			List<FlightJson> allDates=gson.fromJson(inputData,type);
			String date=Date.valueOf(LocalDate.of(journeyDate.get(Calendar.YEAR), journeyDate.get(Calendar.MONTH)+1, journeyDate.get(Calendar.DAY_OF_MONTH))).toString();
			for(FlightJson fj:allDates){
				if(date.equals(fj.getBookingDate())){
					return fj;
				}
			}
		}
		catch(Exception e){;
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(e.getMessage(),e);
			throw e;
		}
	  return null;
	}
  
  public Boolean book(Flight flight) throws Exception{
	  try{
		  return Factory.createFlightDAO().book(flight);
	  }
	  catch(Exception e){
		  Logger logger = Logger.getLogger(this.getClass());
		  logger.error(e.getMessage(),e);
		  throw e;
	  }
  }
  
  public String updateSeats(Calendar journeyDate,String inputData,String travelClass,Integer quantity) throws Exception{
	  try{
		  Gson gson=new Gson();
		  Type type=new TypeToken<List<FlightJson>>() {}.getType();
		  List<FlightJson> allDates=gson.fromJson(inputData,type);
		  String date=Date.valueOf(LocalDate.of(journeyDate.get(Calendar.YEAR), journeyDate.get(Calendar.MONTH)+1, journeyDate.get(Calendar.DAY_OF_MONTH))).toString();
		  for(FlightJson fj:allDates){
			if(date.equals(fj.getBookingDate())){
				if(travelClass.equals("economy")){
					if(fj.getEconomy()>=quantity)
						fj.setEconomy(fj.getEconomy()-quantity);
					else
						throw new Exception("Flight.NOT_ENOUGH_SEATS");
				}
				else if(travelClass.equals("businessClass")){
					if(fj.getBusinessClass()>=quantity)
						fj.setBusinessClass(fj.getBusinessClass()-quantity);
					else
						throw new Exception("Flight.NOT_ENOUGH_SEATS");
				}
				else if(travelClass.equals("firstClass")){
					if(fj.getFirstClass()>=quantity)
						fj.setFirstClass(fj.getFirstClass()-quantity);
					else
						throw new Exception("Flight.NOT_ENOUGH_SEATS");
				}
				else{
					
				}
				break;
			}
		  }
		  return gson.toJson(allDates,type);
	  }
	  catch(Exception e){
		  Logger logger = Logger.getLogger(this.getClass());
		  logger.error(e.getMessage(),e);
		  throw e;
	  }
  }
  
  public Flight calculatePrice(Flight flight) throws Exception{
	  try{
		  Double newPrice;
		  Long daysBetween=Factory.createBookService().calculateDays(flight.getJourneyDate());
		  if(daysBetween>=30){
			  newPrice=flight.getPrice()-flight.getPrice()*(0.1);
			  flight.setPrice(newPrice);
			  flight.setMessage("Congrats! You are getting a discount of 10% on base fare.");
		  }
		  else if(daysBetween<10 && daysBetween>0){
			  newPrice=flight.getPrice()+flight.getPrice()*(0.2);
			  flight.setPrice(newPrice);
			  flight.setMessage("Ticket cost is 20% higher than normal base fare on the selected date.");
		  }
		  else if(daysBetween==0){
			  newPrice=flight.getPrice()+flight.getPrice()*(0.3);
			  flight.setPrice(newPrice);
			  flight.setMessage("Ticket cost is 30% higher than normal base fare today.");
		  }
		  else
		  {
			  flight.setMessage("Ticket prices are normal for the selected date.");
		  }
	  }
	  catch(Exception e){
		  Logger logger = Logger.getLogger(this.getClass());
		  logger.error(e.getMessage(),e);
		  throw e;
	  }
	  return flight;
  }
}
