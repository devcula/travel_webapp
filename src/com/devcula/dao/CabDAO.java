package com.devcula.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.devcula.bean.Cab;
import com.devcula.bean.Hotel;
import com.devcula.entity.CabEntity;
import com.devcula.json.CabJson;
import com.devcula.resources.Factory;
import com.devcula.resources.HibernateUtility;

public class CabDAO {
	public List<String> fetchPlaces(Hotel hotel) throws Exception{
		SessionFactory factory=null;
		Session session=null;
		try{
			factory=HibernateUtility.createSessionFactory();
			session=factory.openSession();
			String hql="Select pe.place from PlaceEntity pe where pe.city=?";
			Query query=session.createQuery(hql);
			query.setString(0, hotel.getCity());
			List<String> list=query.list();
			if(list.size()==0)
				throw new Exception("City.NO_PLACES");
			else
				return list;
		}
		catch(Exception e){
			e.printStackTrace();
			Logger logger=Logger.getLogger(this.getClass());
			logger.error(e.getMessage(),e);
			if(e.getMessage().contains("City"))
				throw e;
			else
				throw new Exception("DAO.TECHNICAL_ERROR");
		}
		finally{
			if(session.isOpen() ||session!=null)
				session.close();
		}
	}
	
	public List<Cab> checkAvailability(Cab cab) throws Exception{
	    SessionFactory factory=null;
	    Session session=null;
	    List<Cab> resultList=null;
	    try{
	      factory=HibernateUtility.createSessionFactory();
	      session=factory.openSession();
	      String hql="Select ce from CabEntity ce where ce.city=? and ce.carType=?";
	      Query query=session.createQuery(hql);
	      query.setString(0,cab.getCity());
	      query.setString(1, cab.getCarType());
	      List<CabEntity> ceList=query.list();
	      if(ceList!=null){
	    	  resultList=new ArrayList<>();
	    	  Iterator<CabEntity> iter=ceList.iterator();
	    	  while(iter.hasNext()){
	    		  	CabEntity ce=iter.next();
	    			Cab temp=new Cab();
	    			temp.setSource(cab.getSource());
	    			temp.setDestination(cab.getDestination());
	    			temp.setJourneyDate(cab.getJourneyDate());
	    			temp.setCarType(cab.getCarType());
	    			temp.setCabNumber(ce.getCabNumber());
	    			temp.setDriverName(ce.getDriverName());
	    			temp.setCabName(ce.getCabName());
	    			temp.setCity(cab.getCity());
	    			CabJson bookingData=Factory.createCabService().filterBookingData(cab.getJourneyDate(),ce.getBookingData());
	    			if(bookingData==null){
	    				throw new Exception("Cab.BOOKING_NOT_STARTED");
	    			}
	    			temp.setBookingDate(bookingData.getBookingDate());
	    			if(cab.getCarType().equals("hatchback") && bookingData.getCabs()>=1){
	    				temp.setPrice(Cab.HATCHBACK);
	    				resultList.add(temp);
	    			}
	    			else if(cab.getCarType().equals("sedan") && bookingData.getCabs()>=1){
	    				temp.setPrice(Cab.SEDAN);
	    				resultList.add(temp);
	    			}
	    			else if(cab.getCarType().equals("suv") && bookingData.getCabs()>=1){
	    				temp.setPrice(Cab.SUV);
	    				resultList.add(temp);
	    			}
	    			else{
	   
	    			}
	    		}
	    	  if(resultList.size()==0){
	  			throw new Exception("Cab.NO_CABS");
	  		}
	      }
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
				Logger logger =Logger.getLogger(this.getClass());
				logger.error(e.getMessage(),e);
				if(e.getMessage().contains("Cab"))
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
	
	public Boolean book(Cab cab) throws Exception{
		SessionFactory factory=null;
		Session session=null;
		Boolean result=false;
		try{
			factory=HibernateUtility.createSessionFactory();
			session=factory.openSession();
			CabEntity ce=(CabEntity)session.get(CabEntity.class, cab.getCabNumber());
			String newBookingData=Factory.createCabService().updateSeats(cab.getJourneyDate(),ce.getBookingData());
			session.getTransaction().begin();
			ce.setBookingData(newBookingData);
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
