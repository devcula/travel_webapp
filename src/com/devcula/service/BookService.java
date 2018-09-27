package com.devcula.service;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.List;

import com.google.gson.reflect.TypeToken;

import org.apache.log4j.Logger;

import com.devcula.bean.Book;
import com.devcula.bean.Booking;
import com.devcula.bean.Bus;
import com.devcula.bean.Cab;
import com.devcula.bean.Flight;
import com.devcula.bean.Hotel;
import com.devcula.bean.Passenger;
import com.devcula.bean.Train;
import com.devcula.bean.User;
import com.devcula.resources.Factory;
import com.devcula.resources.JSONParser;
import com.google.gson.Gson;

public class BookService {
  public Booking processBooking(Book book) throws Exception{
    try{
      if(book.getBookingType().equals("flight"))
        return this.processFlightBooking(book);
      else if(book.getBookingType().equals("train"))
        return this.processTrainBooking(book);
      else if(book.getBookingType().equals("cab"))
        return this.processCabBooking(book);
      else if(book.getBookingType().equals("bus"))
    	  return this.processBusBooking(book);
      else if(book.getBookingType().equals("hotel"))
    	  return this.processHotelBooking(book);
      else
      throw new Exception("Service.WRONG_BOOKING_TYPE");
    }
    catch(Exception e){
      Logger logger = Logger.getLogger(this.getClass());
      logger.error(e.getMessage(),e);
      throw e;
    }
  }

  private Booking processFlightBooking(Book book) throws Exception{
    Booking booking=null;
    try{
      Flight flight=JSONParser.fromJson(book.getBookingObject(),Flight.class);
      Gson gson=new Gson();
      Type type=new TypeToken<List<Passenger>>(){}.getType();
      List<Passenger> passengers=gson.fromJson(book.getPassengerArray(), type);
      if(Factory.createFlightService().book(flight)){
    	  booking=new Booking();
    	  Calendar today=Calendar.getInstance();
    	  LocalDate ld=LocalDate.of(today.get(Calendar.YEAR),today.get(Calendar.MONTH)+1, today.get(Calendar.DAY_OF_MONTH));
    	  booking.setBookingDate(ld.toString());
    	  booking.setBookingType(book.getBookingType().toUpperCase());
    	  booking.setTravelClass(flight.getTravelClass().toUpperCase());
    	  booking.setCanBeCancelled('Y');
    	  booking.setDestination(flight.getDestination());
    	  booking.setFare(flight.getPrice());
    	  booking.setEntityId(flight.getFlightNumber().toString());
    	  booking.setEntityName(flight.getFlightName());
    	  booking.setArrival(flight.getArrival());
    	  booking.setDeparture(flight.getDeparture());
    	  Calendar journeyDate=flight.getJourneyDate();
    	  ld=LocalDate.of(journeyDate.get(Calendar.YEAR),journeyDate.get(Calendar.MONTH)+1, journeyDate.get(Calendar.DAY_OF_MONTH));
    	  booking.setJourneyDate(ld.toString());
    	  booking.setNoOfPassengers(passengers.size());
    	  booking.setSource(flight.getSource());
    	  booking.setTransactionId(this.generateTransactionId());
    	  booking.setUserEmailId(book.getUserEmailId());
    	  booking=this.saveBooking(booking,passengers);
      }
    }
    catch(Exception e){
      Logger logger = Logger.getLogger(this.getClass());
      logger.error(e.getMessage(),e);
      throw e;
    }
    return booking;
  }

  private Booking processTrainBooking(Book book) throws Exception{
	  Booking booking=null;
	    try{
	      Train train=JSONParser.fromJson(book.getBookingObject(),Train.class);
	      Gson gson=new Gson();
	      Type type=new TypeToken<List<Passenger>>(){}.getType();
	      List<Passenger> passengers=gson.fromJson(book.getPassengerArray(), type);
	      if(Factory.createTrainService().book(train)){
	    	  booking=new Booking();
	    	  Calendar today=Calendar.getInstance();
	    	  LocalDate ld=LocalDate.of(today.get(Calendar.YEAR),today.get(Calendar.MONTH)+1, today.get(Calendar.DAY_OF_MONTH));
	    	  booking.setBookingDate(ld.toString());
	    	  booking.setBookingType(book.getBookingType().toUpperCase());
	    	  booking.setTravelClass(train.getTravelClass().toUpperCase());
	    	  booking.setDestination(train.getDestination());
	    	  booking.setFare(train.getPrice());
	    	  booking.setCanBeCancelled('Y');
	    	  booking.setEntityId(train.getTrainNumber().toString());
	    	  booking.setEntityName(train.getTrainName());
	    	  booking.setArrival(train.getArrival());
	    	  booking.setDeparture(train.getDeparture());
	    	  Calendar journeyDate=train.getJourneyDate();
	    	  ld=LocalDate.of(journeyDate.get(Calendar.YEAR),journeyDate.get(Calendar.MONTH)+1, journeyDate.get(Calendar.DAY_OF_MONTH));
	    	  booking.setJourneyDate(ld.toString());
	    	  booking.setNoOfPassengers(passengers.size());
	    	  booking.setSource(train.getSource());
	    	  booking.setTransactionId(this.generateTransactionId());
	    	  booking.setUserEmailId(book.getUserEmailId());
	    	  booking=this.saveBooking(booking,passengers);
	      }
	    }
	    catch(Exception e){
	      Logger logger = Logger.getLogger(this.getClass());
	      logger.error(e.getMessage(),e);
	      throw e;
	    }
	    return booking;
  }

  private Booking processCabBooking(Book book) throws Exception{
	  Booking booking=null;
	    try{
	      Cab cab=JSONParser.fromJson(book.getBookingObject(),Cab.class);
	      Gson gson=new Gson();
	      Type type=new TypeToken<List<Passenger>>(){}.getType();
	      List<Passenger> passengers=gson.fromJson(book.getPassengerArray(), type);
	      if(Factory.createCabService().book(cab)){
	    	  booking=new Booking();
	    	  Calendar today=Calendar.getInstance();
	    	  LocalDate ld=LocalDate.of(today.get(Calendar.YEAR),today.get(Calendar.MONTH)+1, today.get(Calendar.DAY_OF_MONTH));
	    	  booking.setBookingDate(ld.toString());
	    	  booking.setBookingType(book.getBookingType().toUpperCase());
	    	  booking.setTravelClass(cab.getCarType().toUpperCase());
	    	  booking.setDestination(cab.getCity()+", "+cab.getDestination());
	    	  booking.setFare(cab.getPrice());
	    	  booking.setCanBeCancelled('Y');
	    	  booking.setEntityId(cab.getCabNumber());
	    	  booking.setEntityName(cab.getCabName());
	    	  booking.setArrival(cab.getDriverName());
	    	  booking.setDeparture("not required");
	    	  Calendar journeyDate=cab.getJourneyDate();
	    	  ld=LocalDate.of(journeyDate.get(Calendar.YEAR),journeyDate.get(Calendar.MONTH)+1, journeyDate.get(Calendar.DAY_OF_MONTH));
	    	  booking.setJourneyDate(ld.toString());
	    	  booking.setNoOfPassengers(passengers.size());
	    	  booking.setSource(cab.getCity()+", "+cab.getSource());
	    	  booking.setTransactionId(this.generateTransactionId());
	    	  booking.setUserEmailId(book.getUserEmailId());
	    	  booking=this.saveBooking(booking,passengers);
	      }
	    }
	    catch(Exception e){
	      Logger logger = Logger.getLogger(this.getClass());
	      logger.error(e.getMessage(),e);
	      throw e;
	    }
	    return booking;
  }
  
  private Booking processBusBooking(Book book) throws Exception{
	  Booking booking=null;
	    try{
	      Bus bus=JSONParser.fromJson(book.getBookingObject(),Bus.class);
	      Gson gson=new Gson();
	      Type type=new TypeToken<List<Passenger>>(){}.getType();
	      List<Passenger> passengers=gson.fromJson(book.getPassengerArray(), type);
	      if(Factory.createBusService().book(bus)){
	    	  booking=new Booking();
	    	  Calendar today=Calendar.getInstance();
	    	  LocalDate ld=LocalDate.of(today.get(Calendar.YEAR),today.get(Calendar.MONTH)+1, today.get(Calendar.DAY_OF_MONTH));
	    	  booking.setBookingDate(ld.toString());
	    	  booking.setBookingType(book.getBookingType().toUpperCase());
	    	  booking.setTravelClass(bus.getTravelClass().toUpperCase());
	    	  booking.setDestination(bus.getDestination());
	    	  booking.setFare(bus.getPrice());
	    	  booking.setCanBeCancelled('Y');
	    	  booking.setEntityId(bus.getBusNumber().toString());
	    	  booking.setEntityName(bus.getBusName());
	    	  booking.setArrival(bus.getArrival());
	    	  booking.setDeparture(bus.getDeparture());
	    	  Calendar journeyDate=bus.getJourneyDate();
	    	  ld=LocalDate.of(journeyDate.get(Calendar.YEAR),journeyDate.get(Calendar.MONTH)+1, journeyDate.get(Calendar.DAY_OF_MONTH));
	    	  booking.setJourneyDate(ld.toString());
	    	  booking.setNoOfPassengers(passengers.size());
	    	  booking.setSource(bus.getSource());
	    	  booking.setTransactionId(this.generateTransactionId());
	    	  booking.setUserEmailId(book.getUserEmailId());
	    	  booking=this.saveBooking(booking,passengers);
	      }
	    }
	    catch(Exception e){
	      Logger logger = Logger.getLogger(this.getClass());
	      logger.error(e.getMessage(),e);
	      throw e;
	    }
	    return booking;
	  }
  
  public Booking processHotelBooking(Book book) throws Exception{
	  Booking booking=null;
	  try{
		  Hotel hotel=JSONParser.fromJson(book.getBookingObject(),Hotel.class);
	      Gson gson=new Gson();
	      Type type=new TypeToken<List<Passenger>>(){}.getType();
	      List<Passenger> passengers=gson.fromJson(book.getPassengerArray(), type);
	      if(Factory.createHotelService().book(hotel)){
	    	  booking=new Booking();
	    	  Calendar today=Calendar.getInstance();
	    	  LocalDate ld=LocalDate.of(today.get(Calendar.YEAR),today.get(Calendar.MONTH)+1, today.get(Calendar.DAY_OF_MONTH));
	    	  booking.setBookingDate(ld.toString());
	    	  booking.setBookingType(book.getBookingType().toUpperCase());
	    	  booking.setTravelClass(hotel.getRoomsType().toUpperCase());
	    	  booking.setDestination(hotel.getCity());
	    	  booking.setCanBeCancelled('Y');
	    	  booking.setFare(hotel.getPrice());
	    	  booking.setEntityId(hotel.getHotelId().toString());
	    	  booking.setEntityName(hotel.getHotelName());
	    	  Calendar checkInDate=hotel.getCheckInDate();
	    	  Calendar checkOutDate=hotel.getCheckOutDate();
	    	  booking.setArrival(LocalDate.of(checkInDate.get(Calendar.YEAR), checkInDate.get(Calendar.MONTH)+1, checkInDate.get(Calendar.DAY_OF_MONTH)).toString());
	    	  booking.setDeparture(LocalDate.of(checkOutDate.get(Calendar.YEAR), checkOutDate.get(Calendar.MONTH)+1, checkOutDate.get(Calendar.DAY_OF_MONTH)).toString());
	    	  booking.setJourneyDate("Not Required");
	    	  booking.setNoOfPassengers(passengers.size());
	    	  booking.setSource(hotel.getCity());
	    	  booking.setTransactionId(this.generateTransactionId());
	    	  booking.setUserEmailId(book.getUserEmailId());
	    	  booking=this.saveBooking(booking,passengers);
	      }
	  }
	  catch(Exception e){
		  Logger logger = Logger.getLogger(this.getClass());
	      logger.error(e.getMessage(),e);
	      throw e;
	  }
	  return booking;
  }
  
  public Booking saveBooking(Booking booking,List<Passenger> passengers) throws Exception{
	  try{
		  return Factory.createBookDAO().saveBooking(booking,passengers);
	  }
	  catch(Exception e){
		  Logger logger = Logger.getLogger(this.getClass());
	      logger.error(e.getMessage(),e);
	      throw e;
	  }
  }
  
  public List<Booking> fetchUserBookings(String emailId) throws Exception{
	  try{
		  List<Booking> bookings= Factory.createBookDAO().fetchUserBookings(emailId.toLowerCase());
		  Calendar today=Calendar.getInstance();
		  LocalDate todayDate=LocalDate.of(today.get(Calendar.YEAR), today.get(Calendar.MONTH)+1, today.get(Calendar.DAY_OF_MONTH));
		  int size=bookings.size();
		  for(int i=0;i<size;i++){
			  String dateParts[];
			  if(bookings.get(i).getBookingType().toLowerCase().equals("hotel")){
				  dateParts=bookings.get(i).getArrival().split("-");
			  }
			  else{
				  dateParts=bookings.get(i).getJourneyDate().split("-");
			  }
			  LocalDate date=LocalDate.of(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2]));
				 if(date.isAfter(todayDate)){
					 bookings.get(i).setCanBeCancelled('Y');
				 }
				 else
					 bookings.get(i).setCanBeCancelled('N');
		  }
		  for(Booking b:bookings){
			  if(b.getBookingType().equals("HOTEL")){
				  String dateParts[]=b.getDeparture().split("-");
				  LocalDate date=LocalDate.of(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2]));
				  if(todayDate.isAfter(date)){
					  b.setCanBeRated('Y');
				  }
				  else
					  b.setCanBeRated('N');
			  }
		  }
		  return bookings;
	  }
	  catch(Exception e){
		  Logger logger = Logger.getLogger(this.getClass());
	      logger.error(e.getMessage(),e);
	      throw e;
	  }
  }
  
  public String generateTransactionId() throws Exception{
	  
	  try{
		  LocalDateTime now=LocalDateTime.now();
		  String id=now.toString();
		  String newId="";
		  for(int i=0;i<id.length();i++){
			  if(Character.isDigit(id.charAt(i)))
				  newId+=id.charAt(i);
		  }
		  newId=newId.substring(4, newId.length());
		  newId="T"+newId;
		  return newId;
	  }
	  catch(Exception e){
		  Logger logger = Logger.getLogger(this.getClass());
	      logger.error(e.getMessage(),e);
	      throw e;
	  }
  }
  
  public Long calculateDays(Calendar journeyDate){
	  Calendar now=Calendar.getInstance();
	  LocalDate nowLd=LocalDate.of(now.get(Calendar.YEAR), now.get(Calendar.MONTH)+1, now.get(Calendar.DAY_OF_MONTH));
	  LocalDate journey=LocalDate.of(journeyDate.get(Calendar.YEAR), journeyDate.get(Calendar.MONTH)+1, journeyDate.get(Calendar.DAY_OF_MONTH));
	  Long noOfDays=ChronoUnit.DAYS.between(nowLd, journey);
	  return noOfDays;
  }
  
  public void saveFeedback(Booking booking) throws Exception{
	  try{
		  Factory.createBookDAO().saveFeedback(booking);
	  }
	  catch(Exception e){
		  Logger logger = Logger.getLogger(this.getClass());
	      logger.error(e.getMessage(),e);
	      throw e;
	  }
  }
  
  public Hotel checkHotelBookings(User user) throws Exception{
	  try{
		  user.setEmailId(user.getEmailId().toLowerCase());
		  return Factory.createBookDAO().checkHotelBookings(user);
	  }
	  catch(Exception e){
		  Logger logger = Logger.getLogger(this.getClass());
	      logger.error(e.getMessage(),e);
	      throw e;
	  }
  }
  
  public String submitHotelRating(Hotel hotel) throws Exception{
	  try{
		  return Factory.createBookDAO().submitHotelRating(hotel);
	  }
	  catch(Exception e){
		  Logger logger = Logger.getLogger(this.getClass());
	      logger.error(e.getMessage(),e);
	      throw e;
	  }
  }
  
  public void cancelBooking(Booking booking) throws Exception{
	  try{
		  Factory.createBookDAO().cancelBooking(booking);
	  }
	  catch(Exception e){
		  Logger logger = Logger.getLogger(this.getClass());
	      logger.error(e.getMessage(),e);
	      throw e;
	  }
  }
  /*public static void main(String[] args) {
	Calendar test=Calendar.getInstance();
	test.set(2018, 06, 20);
	BookService book=new BookService();
	book.calculateDays(test);
}*/
}
