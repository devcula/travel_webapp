package com.devcula.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.devcula.bean.Bus;
import com.devcula.entity.BusEntity;
import com.devcula.json.BusJson;
import com.devcula.resources.Factory;
import com.devcula.resources.HibernateUtility;

public class BusDAO {
	public List<Bus> checkAvailability(Bus bus) throws Exception{
		SessionFactory factory=null;
		Session session=null;
		List<Bus> resultList=null;
		try{
			factory=HibernateUtility.createSessionFactory();
			session=factory.openSession();
			String hql="Select be from BusEntity be where be.source=? and be.destination=? and be.busType=?";
			Query query=session.createQuery(hql);
			query.setString(0, bus.getSource());
			query.setString(1, bus.getDestination());
			query.setString(2, bus.getTravelClass());
			List<BusEntity> beList=query.list();
			if(beList!=null){
				resultList=new ArrayList<>();
				for(BusEntity be:beList){
					Bus temp=new Bus();
					temp.setArrival(be.getArrival());
					temp.setDestination(bus.getDestination());
					temp.setDeparture(be.getDeparture());
					temp.setSource(bus.getSource());
					temp.setBusNumber(be.getBusNumber());
					temp.setBusName(be.getBusName());
					temp.setJourneyDate(bus.getJourneyDate());
	    			temp.setQuantity(bus.getQuantity());
	    			temp.setTravelClass(bus.getTravelClass());
	    			BusJson bookingData=Factory.createBusService().filterBookingData(bus.getJourneyDate(),be.getBookingData());
	    			if(bookingData==null){
	    				throw new Exception("Bus.BOOKING_NOT_STARTED");
	    			}
	    			temp.setBookingDate(bookingData.getBookingDate());
	    			if(bookingData.getSeats()>=bus.getQuantity()){
	    				if(bus.getTravelClass().equals("AC"))
	    					temp.setPrice(Bus.AC);
	    				else if(bus.getTravelClass().equals("NONAC"))
	    					temp.setPrice(Bus.NONAC);
	    				else{
	    					
	    				}
	    				temp.setAvailableSeats(bookingData.getSeats());
	    				resultList.add(temp);
	    			}
				}
				if(resultList.size()==0){
    				throw new Exception("Bus.NO_BUSES");
    			}
			}
			else
				throw new Exception("Bus.NO_BUSES");
		}
		catch(Exception e){
			e.printStackTrace();
			Logger logger=Logger.getLogger(this.getClass());
			logger.error(e.getMessage(),e);
			if(e.getMessage().contains("Bus"))
				throw e;
			else
				throw new Exception("DAO.TECHNICAL_ERROR");
		}
		finally{
			if(session.isOpen()||session!=null)
				session.close();
		}
		return resultList;
	}
	
	public Boolean book(Bus bus) throws Exception{
		SessionFactory factory=null;
		Session session=null;
		Boolean result=false;
		try{
			factory=HibernateUtility.createSessionFactory();
			session=factory.openSession();
			BusEntity be=(BusEntity)session.get(BusEntity.class, bus.getBusNumber());
			String newBookingData=Factory.createBusService().updateSeats(bus.getJourneyDate(),be.getBookingData(),bus.getQuantity());
			session.getTransaction().begin();
			be.setBookingData(newBookingData);
			session.getTransaction().commit();
			result=true;
		}
		catch(Exception e){
			Logger logger =Logger.getLogger(this.getClass());
			logger.error(e.getMessage(),e);
			if(e.getMessage().contains("Bus"))
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
