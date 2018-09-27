package com.devcula.service;

import java.lang.reflect.Type;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import com.devcula.bean.Hotel;
import com.devcula.json.HotelJson;
import com.devcula.resources.Factory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class HotelService {
	public List<Hotel> checkAvailability(Hotel hotel) throws Exception{
		try{
			//System.out.println("In service: check in date: "+hotel.getCheckInDate().get(Calendar.DAY_OF_MONTH));
			return Factory.createHotelDAO().checkAvailability(hotel);
		}
		catch(Exception e){
			e.printStackTrace();
			Logger logger=Logger.getLogger(this.getClass());
			logger.error(e.getMessage(),e);
			throw e;
		}
	}
	
	public Boolean checkBookingData(Calendar checkInDate,Calendar checkOutDate,String inputData,Integer quantity) throws Exception{
		try{
			Calendar temp=checkInDate;
			Gson gson=new Gson();
			Type type=new TypeToken<List<HotelJson>>() {}.getType();
			List<HotelJson> allDates=gson.fromJson(inputData,type);
			while(temp.before(checkOutDate)){
				String date=Date.valueOf(LocalDate.of(temp.get(Calendar.YEAR), temp.get(Calendar.MONTH)+1, temp.get(Calendar.DAY_OF_MONTH))).toString();
				for(HotelJson hj:allDates){
					if(date.equals(hj.getBookingDate())){
						if(hj.getRooms()<quantity)
							return false;
					}
				}
				temp.add(Calendar.DATE, 1);
			}
			return true;
		}
		catch(Exception e){
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(e.getMessage(),e);
			throw e;
		}
	}
	
	public Boolean book(Hotel hotel) throws Exception{
		try{
			return Factory.createHotelDAO().book(hotel);
		}
		catch(Exception e){
			Logger logger = Logger.getLogger(this.getClass());
			  logger.error(e.getMessage(),e);
			  throw e;
		}
	}
	
	public String updateRooms(Calendar checkInDate,Calendar checkOutDate,String inputData,Integer quantity) throws Exception{
		try{
			Gson gson=new Gson();
			Type type=new TypeToken<List<HotelJson>>() {}.getType();
			List<HotelJson> allDates=gson.fromJson(inputData,type);
			/*while(checkInDate.before(checkOutDate)){
				String date=Date.valueOf(LocalDate.of(checkInDate.get(Calendar.YEAR), checkInDate.get(Calendar.MONTH)+1, checkInDate.get(Calendar.DAY_OF_MONTH))).toString();
				for(HotelJson hj:allDates){
					if(date.equals(hj.getBookingDate())){
						if(hj.getRooms()>=quantity){
							hj.setRooms(hj.getRooms()-quantity);
						}
						else
							throw new Exception("Hotel.NOT_ENOUGH_ROOMS");
					}
				}
				checkInDate.add(Calendar.DATE, 1);
			}*/
			return gson.toJson(allDates,type);
		}
		catch(Exception e){
			 Logger logger = Logger.getLogger(this.getClass());
			  logger.error(e.getMessage(),e);
			  throw e;
		}
	}
	
	public Hotel findHotels(Hotel hotel) throws Exception{
		try{
			return Factory.createHotelDAO().findHotels(hotel);
		}
		catch(Exception e){
			Logger logger = Logger.getLogger(this.getClass());
			  logger.error(e.getMessage(),e);
			  throw e;
		}
	}
}
