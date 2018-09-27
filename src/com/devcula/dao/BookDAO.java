package com.devcula.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.devcula.bean.Booking;
import com.devcula.bean.Hotel;
import com.devcula.bean.Passenger;
import com.devcula.bean.User;
import com.devcula.entity.BookingEntity;
import com.devcula.entity.HotelEntity;
import com.devcula.entity.PassengerEntity;
import com.devcula.resources.HibernateUtility;

public class BookDAO {
	 public Booking saveBooking(Booking booking,List<Passenger> passengers) throws Exception{
		 SessionFactory factory=null;
		 Session session=null;
		 try{
			 factory=HibernateUtility.createSessionFactory();
			 session=factory.openSession();
			 BookingEntity be=new BookingEntity();
			 be.setBookingDate(booking.getBookingDate());
			 be.setBookingType(booking.getBookingType());
			 be.setDestination(booking.getDestination());
			 be.setFare(booking.getFare());
			 be.setTravelClass(booking.getTravelClass());
			 be.setJourneyDate(booking.getJourneyDate());
			 be.setNoOfPassengers(booking.getNoOfPassengers());
			 be.setSource(booking.getSource());
			 be.setTransactionId(booking.getTransactionId());
			 be.setUserEmailId(booking.getUserEmailId());
			 be.setEntityId(booking.getEntityId());
			 be.setEntityName(booking.getEntityName());
			 be.setArrival(booking.getArrival());
			 be.setDeparture(booking.getDeparture());
			 be.setFeedback("null");
			 be.setRated('N');
			 session.getTransaction().begin();
			 session.persist(be);
			 session.getTransaction().commit();
			 booking.setBookingId(be.getBookingId());
			 for(Passenger p:passengers){
				 PassengerEntity pe=new PassengerEntity();
				 pe.setAge(p.getAge());
				 pe.setBookingId(booking.getBookingId());
				 pe.setGender(p.getGender());
				 pe.setName(p.getName());
				 session.getTransaction().begin();
				 session.persist(pe);
				 session.getTransaction().commit();
			 }
		 }
		 catch(Exception e){
			 e.printStackTrace();
			 Logger logger = Logger.getLogger(this.getClass());
		     logger.error(e.getMessage(),e);
		     throw new Exception("DAO.TECHNICAL_ERROR");
		  }
		 finally{
			 if(session.isOpen() || session!=null)
				 session.close();
		 }
		 return booking;
	 }
	 
	 public List<Booking> fetchUserBookings(String emailId) throws Exception{
		 SessionFactory factory=null;
		 Session session=null;
		 List<Booking> bookings=new ArrayList<Booking>();
		 try{
			 factory=HibernateUtility.createSessionFactory();
			 session=factory.openSession();
			 String hql="Select be from BookingEntity be where be.userEmailId=?";
			 Query query=session.createQuery(hql);
			 query.setString(0, emailId);
			 List<BookingEntity> list=query.list();
			 for(BookingEntity be:list){
				 Booking booking=new Booking();
				 booking.setBookingDate(be.getBookingDate());
				 booking.setBookingId(be.getBookingId());
				 booking.setBookingType(be.getBookingType());
				 booking.setTravelClass(be.getTravelClass());
				 booking.setDestination(be.getDestination());
				 booking.setEntityId(be.getEntityId());
				 booking.setEntityName(be.getEntityName());
				 booking.setFare(be.getFare());
				 booking.setJourneyDate(be.getJourneyDate());
				 booking.setNoOfPassengers(be.getNoOfPassengers());
				 booking.setSource(be.getSource());
				 booking.setTransactionId(be.getTransactionId());
				 booking.setUserEmailId(be.getUserEmailId());
				 booking.setArrival(be.getArrival());
				 booking.setDeparture(be.getDeparture());
				 booking.setFeedback(be.getFeedback());
				 booking.setRated(be.getRated());
				 bookings.add(booking);
			 }
			 if(bookings.size()==0){
				 throw new Exception("Book.NO_BOOKINGS_FOUND");
			 }
		 }
		 catch(Exception e){
			 Logger logger = Logger.getLogger(this.getClass());
			 logger.error(e.getMessage(),e);
			 if(e.getMessage().contains("Book"))
				 throw e;
			 else
				 throw new Exception("DAO.TECHNICAL_ERROR");
		  }
		 finally{
			 if(session.isOpen() || session!=null)
				 session.close();
		 }
		 return bookings;
	 }
	 
	 public void saveFeedback(Booking booking) throws Exception{
		 SessionFactory factory=null;
		 Session session=null;
		 try{
			 factory=HibernateUtility.createSessionFactory();
			 session=factory.openSession();
			 BookingEntity be=(BookingEntity)session.get(BookingEntity.class, booking.getBookingId());
			 session.getTransaction().begin();
			 be.setFeedback(booking.getFeedback());
			 session.getTransaction().commit();
		 }
		 catch(Exception e){
			 Logger logger=Logger.getLogger(this.getClass());
			 logger.error(e.getMessage(),e);
			 throw e;
		 }
		 finally{
			 if(session.isOpen() || session!=null)
				 session.close();
		 }
	 }
	 
	 public Hotel checkHotelBookings(User user) throws Exception{
		 SessionFactory factory=null;
		 Session session=null;
		 try{
			 factory=HibernateUtility.createSessionFactory();
			 session=factory.openSession();
			 String hql="Select be from BookingEntity be where be.userEmailId=? and be.bookingType=? and rated=? order by be.departure";
			 Query query=session.createQuery(hql);
			 query.setString(0, user.getEmailId());
			 query.setString(1, "HOTEL");
			 query.setCharacter(2, 'N');
			 List<BookingEntity> beList=query.list();
			 Calendar today=Calendar.getInstance();
			 LocalDate todayDate=LocalDate.of(today.get(Calendar.YEAR), today.get(Calendar.MONTH)+1, today.get(Calendar.DAY_OF_MONTH));
			 int size=beList.size();
			 for(int i=0;i<size;i++){
				 String dateParts[]=beList.get(i).getDeparture().split("-");
				 LocalDate checkOutDate=LocalDate.of(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2]));
				 if(checkOutDate.isAfter(todayDate)){
					 beList.remove(i);
					 size--;
					 i--;
				 }
			 }
			 if(beList.size()!=0){
				 BookingEntity be=beList.get(0);
				 Hotel hotel=new Hotel();
				 hotel.setHotelId(Integer.parseInt(be.getEntityId()));
				 hotel.setHotelName(be.getEntityName());
				 hotel.setCity(be.getSource());
				 hotel.setPrice(new Double(be.getBookingId())); //Setting booking id into the price variable to modify rated variable later in submit hotel rating.
				 System.out.println(be.getDeparture());
				 return hotel;
			 }
			 else
				 throw new Exception("No hotel bookings available to rate.");
		 }
		 catch(Exception e){
			 //e.printStackTrace();
			 Logger logger=Logger.getLogger(this.getClass());
			 logger.error(e.getMessage(),e);
			 if(e.getMessage().contains("hotel"))
				 throw e;
			 else
				 throw new Exception("DAO.TECHNICAL_ERROR");
		 }
		 finally{
			 if(session.isOpen() || session!=null)
				 session.close();
		 }
	 }
	 public String submitHotelRating(Hotel hotel) throws Exception{
		 SessionFactory factory=null;
		 Session session=null;
		 String result=null;
		 try{
			 factory=HibernateUtility.createSessionFactory();
			 session=factory.openSession();
			 HotelEntity he=(HotelEntity)session.get(HotelEntity.class,hotel.getHotelId());
			 if(he!=null){
				 Double previousRating=he.getRating();
				 Double previousTimesRated=he.getTimesRated();
				 previousRating=(previousRating*previousTimesRated+hotel.getRating())/(previousTimesRated+1);
				 previousTimesRated+=1;
				 session.getTransaction().begin();
				 he.setRating(previousRating);
				 he.setTimesRated(previousTimesRated);
				 session.getTransaction().commit();
				 BookingEntity be=(BookingEntity)session.get(BookingEntity.class, hotel.getPrice().intValue());
				 if(be!=null){
					 session.getTransaction().begin();
					 be.setRated('Y');
					 session.getTransaction().commit();
				 }
				 result="Rating saved successfully";
			 }
			 else
				 throw new Exception("Hotel Not found");
		 }
		 catch(Exception e){
			 e.printStackTrace();
			 Logger logger=Logger.getLogger(this.getClass());
			 logger.error(e.getMessage(),e);
			 if(e.getMessage().contains("hotel"))
				 throw e;
			 else
				 throw new Exception("DAO.TECHNICAL_ERROR");
		 }
		 finally{
			 if(session.isOpen() || session!=null)
				 session.close();
		 }
		 return result;
	 }
	 
	 public void cancelBooking(Booking booking) throws Exception{
		 SessionFactory factory=null;
		 Session session=null;
		 try{
			 factory=HibernateUtility.createSessionFactory();
			 session=factory.openSession();
			 BookingEntity be=(BookingEntity)session.get(BookingEntity.class,booking.getBookingId());
			 if(be!=null){
				 session.getTransaction().begin();
				 session.delete(be);
				 session.getTransaction().commit();
			 }
		 }
		 catch(Exception e){
			 Logger logger=Logger.getLogger(this.getClass());
			 logger.error(e.getMessage(),e);
			 throw new Exception("DAO.TECHNICAL_ERROR");
		 }
		 finally{
			 if(session.isOpen() || session!=null)
				 session.close();
		 }
	 }
}