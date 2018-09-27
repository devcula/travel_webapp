package com.devcula.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Query;

import com.devcula.bean.Flight;
import com.devcula.entity.FlightEntity;
import com.devcula.json.FlightJson;
import com.devcula.resources.Factory;
import com.devcula.resources.HibernateUtility;

import org.apache.log4j.Logger;

public class FlightDAO{

	public List<Flight> checkAvailability(Flight flight) throws Exception{
    SessionFactory factory=null;
    Session session=null;
    List<Flight> resultList=null;
    try{
      factory=HibernateUtility.createSessionFactory();
      session=factory.openSession();
      String hql="Select fe from FlightEntity fe where fe.source=? and fe.destination=?";
      Query query=session.createQuery(hql);
      query.setString(0,flight.getSource());
      query.setString(1,flight.getDestination());
      List<FlightEntity> feList=query.list();
      if(feList!=null){
    	  resultList=new ArrayList<>();
    	  Iterator<FlightEntity> iter=feList.iterator();
    	  while(iter.hasNext()){
    		  	FlightEntity fe=iter.next();
    			Flight temp=new Flight();
    			temp.setSource(flight.getSource());
    			temp.setDestination(flight.getDestination());
    			temp.setJourneyDate(flight.getJourneyDate());
    			temp.setQuantity(flight.getQuantity());
    			temp.setTravelClass(flight.getTravelClass());
    			temp.setFlightNumber(fe.getFlightNumber());
    			temp.setArrival(fe.getArrival());
    			temp.setDeparture(fe.getDeparture());
    			temp.setFlightName(fe.getFlightName());
    			FlightJson bookingData=Factory.createFlightService().filterBookingData(flight.getJourneyDate(),fe.getBookingData());
    			if(bookingData==null){
    				throw new Exception("Flight.BOOKING_NOT_STARTED");
    			}
    			temp.setBookingDate(bookingData.getBookingDate());
    			if(flight.getTravelClass().equals("Economy") && bookingData.getEconomy()>=flight.getQuantity()){
    				temp.setPrice(Flight.ECONOMY);
    				temp=Factory.createFlightService().calculatePrice(temp);
    				resultList.add(temp);
    			}
    			else if(flight.getTravelClass().equals("BusinessClass") && bookingData.getBusinessClass()>=flight.getQuantity()){
    				temp.setPrice(Flight.BUSINESS);
    				temp=Factory.createFlightService().calculatePrice(temp);
    				resultList.add(temp);
    			}
    			else if(flight.getTravelClass().equals("FirstClass") && bookingData.getFirstClass()>=flight.getQuantity()){
    				temp.setPrice(Flight.FIRST);
    				temp=Factory.createFlightService().calculatePrice(temp);
    				resultList.add(temp);
    			}
    			else{
   
    			}
    		}
    	  if(resultList.size()==0){
  			throw new Exception("Flight.NO_FLIGHTS");
  		}
      }
    }
    catch (Exception e) {
    	e.printStackTrace();
			Logger logger =Logger.getLogger(this.getClass());
			logger.error(e.getMessage(),e);
			if(e.getMessage().contains("Flight"))
				throw e;
			else
				throw new Exception("DAO.TECHNICAL_ERROR");
    }
		finally{
			if(session.isOpen() || session!=null)
				session.close();
		}
		return resultList;
  }
	
	public Boolean book(Flight flight) throws Exception{
		SessionFactory factory=null;
		Session session=null;
		Boolean result=false;
		try{
			factory=HibernateUtility.createSessionFactory();
			session=factory.openSession();
			FlightEntity fe=(FlightEntity)session.get(FlightEntity.class, flight.getFlightNumber());
			String newBookingData=Factory.createFlightService().updateSeats(flight.getJourneyDate(),fe.getBookingData(),flight.getTravelClass(),flight.getQuantity());
			session.getTransaction().begin();
			fe.setBookingData(newBookingData);
			session.getTransaction().commit();
			result=true;
		}
		catch(Exception e){
			Logger logger =Logger.getLogger(this.getClass());
			logger.error(e.getMessage(),e);
			if(e.getMessage().contains("Flight"))
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
	
}
