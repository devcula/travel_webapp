package com.devcula.service;

import java.lang.reflect.Type;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import com.devcula.bean.Cab;
import com.devcula.bean.Hotel;
import com.devcula.json.CabJson;
import com.devcula.resources.Factory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class CabService {
	public List<String> fetchPlaces(Hotel hotel) throws Exception{
		try{
			return Factory.createCabDAO().fetchPlaces(hotel);
		}
		catch(Exception e){
			Logger logger=Logger.getLogger(this.getClass());
			logger.error(e.getMessage(),e);
			throw e;
		}
	}
	
	public List<Cab> checkAvailability(Cab cab) throws Exception{
	    try{
	      return Factory.createCabDAO().checkAvailability(cab);
	    }
	    catch(Exception e){
	      Logger logger=Logger.getLogger(this.getClass());
	      logger.error(e.getMessage(),e);
	      throw e;
	    }
	  }
	
	public CabJson filterBookingData(Calendar journeyDate, String inputData) throws Exception{
		try{
			Gson gson=new Gson();
			Type type=new TypeToken<List<CabJson>>() {}.getType();
			List<CabJson> allDates=gson.fromJson(inputData,type);
			String date=Date.valueOf(LocalDate.of(journeyDate.get(Calendar.YEAR), journeyDate.get(Calendar.MONTH)+1, journeyDate.get(Calendar.DAY_OF_MONTH))).toString();
			for(CabJson bj:allDates){
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
	
	public Boolean book(Cab cab) throws Exception{
		  try{
			  return Factory.createCabDAO().book(cab);
		  }
		  catch(Exception e){
			  Logger logger = Logger.getLogger(this.getClass());
			  logger.error(e.getMessage(),e);
			  throw e;
		  }
	  }
	
	public String updateSeats(Calendar journeyDate,String inputData) throws Exception{
		  try{
			  Gson gson=new Gson();
			  Type type=new TypeToken<List<CabJson>>() {}.getType();
			  List<CabJson> allDates=gson.fromJson(inputData,type);
			  String date=Date.valueOf(LocalDate.of(journeyDate.get(Calendar.YEAR), journeyDate.get(Calendar.MONTH)+1, journeyDate.get(Calendar.DAY_OF_MONTH))).toString();
			  for(CabJson cj:allDates){
				if(date.equals(cj.getBookingDate())){
						if(cj.getCabs()>=1)
							cj.setCabs(cj.getCabs()-1);
						else
							throw new Exception("Cab.NOT_ENOUGH_CABS");
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
