package com.devcula.service;

import java.lang.reflect.Type;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import com.devcula.bean.Train;
import com.devcula.json.TrainJson;
import com.devcula.resources.Factory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class TrainService {
	
	public List<Train> checkAvailability(Train train) throws Exception{
		try{
			return Factory.createTrainDAO().checkAvailability(train);
		}
		catch(Exception e){
			Logger logger=Logger.getLogger(this.getClass());
			logger.error(e.getMessage(),e);
			throw e;
		}
	}
	
	public TrainJson filterBookingData(Calendar journeyDate, String inputData) throws Exception{
		try{
			Gson gson=new Gson();
			Type type=new TypeToken<List<TrainJson>>() {}.getType();
			List<TrainJson> allDates=gson.fromJson(inputData,type);
			String date=Date.valueOf(LocalDate.of(journeyDate.get(Calendar.YEAR), journeyDate.get(Calendar.MONTH)+1, journeyDate.get(Calendar.DAY_OF_MONTH))).toString();
			for(TrainJson tj:allDates){
				if(date.equals(tj.getBookingDate())){
					return tj;
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
	
	public Boolean book(Train train) throws Exception{
		  try{
			  return Factory.createTrainDAO().book(train);
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
			  Type type=new TypeToken<List<TrainJson>>() {}.getType();
			  List<TrainJson> allDates=gson.fromJson(inputData,type);
			  String date=Date.valueOf(LocalDate.of(journeyDate.get(Calendar.YEAR), journeyDate.get(Calendar.MONTH)+1, journeyDate.get(Calendar.DAY_OF_MONTH))).toString();
			  for(TrainJson tj:allDates){
				if(date.equals(tj.getBookingDate())){
					if(travelClass.equals("sleeper")){
						if(tj.getSleeper()>=quantity)
							tj.setSleeper(tj.getSleeper()-quantity);
						else
							throw new Exception("Train.NOT_ENOUGH_SEATS");
					}
					else if(travelClass.equals("secondAC")){
						if(tj.getSecondAC()>=quantity)
							tj.setSecondAC(tj.getSecondAC()-quantity);
						else
							throw new Exception("Train.NOT_ENOUGH_SEATS");
					}
					else if(travelClass.equals("thirdAC")){
						if(tj.getThirdAC()>=quantity)
							tj.setThirdAC(tj.getThirdAC()-quantity);
						else
							throw new Exception("Train.NOT_ENOUGH_SEATS");
					}
					else if(travelClass.equals("firstAC")){
						if(tj.getFirstAC()>=quantity)
							tj.setFirstAC(tj.getFirstAC()-quantity);
						else
							throw new Exception("Train.NOT_ENOUGH_SEATS");
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
}
