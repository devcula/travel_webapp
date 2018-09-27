package com.devcula.service;

import java.lang.reflect.Type;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import com.devcula.bean.Bus;
import com.devcula.json.BusJson;
import com.devcula.resources.Factory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class BusService {
	public List<Bus> checkAvailability(Bus bus) throws Exception{
		try{
			return Factory.createBusDAO().checkAvailability(bus);
		}
		catch(Exception e){
			Logger logger=Logger.getLogger(this.getClass());
			logger.error(e.getMessage(),e);
			throw e;
		}
	}
	public BusJson filterBookingData(Calendar journeyDate, String inputData) throws Exception{
		try{
			Gson gson=new Gson();
			Type type=new TypeToken<List<BusJson>>() {}.getType();
			List<BusJson> allDates=gson.fromJson(inputData,type);
			String date=Date.valueOf(LocalDate.of(journeyDate.get(Calendar.YEAR), journeyDate.get(Calendar.MONTH)+1, journeyDate.get(Calendar.DAY_OF_MONTH))).toString();
			for(BusJson bj:allDates){
				if(date.equals(bj.getBookingDate())){
					return bj;
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
	
	public Boolean book(Bus bus) throws Exception{
		  try{
			  return Factory.createBusDAO().book(bus);
		  }
		  catch(Exception e){
			  Logger logger = Logger.getLogger(this.getClass());
			  logger.error(e.getMessage(),e);
			  throw e;
		  }
	  }
	public String updateSeats(Calendar journeyDate,String inputData,Integer quantity) throws Exception{
		  try{
			  Gson gson=new Gson();
			  Type type=new TypeToken<List<BusJson>>() {}.getType();
			  List<BusJson> allDates=gson.fromJson(inputData,type);
			  String date=Date.valueOf(LocalDate.of(journeyDate.get(Calendar.YEAR), journeyDate.get(Calendar.MONTH)+1, journeyDate.get(Calendar.DAY_OF_MONTH))).toString();
			  for(BusJson tj:allDates){
				if(date.equals(tj.getBookingDate())){
						if(tj.getSeats()>=quantity)
							tj.setSeats(tj.getSeats()-quantity);
						else
							throw new Exception("Bus.NOT_ENOUGH_SEATS");
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
}
