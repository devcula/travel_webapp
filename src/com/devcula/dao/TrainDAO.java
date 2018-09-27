package com.devcula.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.devcula.bean.Train;
import com.devcula.entity.TrainEntity;
import com.devcula.json.TrainJson;
import com.devcula.resources.Factory;
import com.devcula.resources.HibernateUtility;

public class TrainDAO {
	public List<Train> checkAvailability(Train train) throws Exception{
		SessionFactory factory=null;
		Session session=null;
		List<Train> resultList=null;
		try{
			factory=HibernateUtility.createSessionFactory();
			session=factory.openSession();
			String hql="Select te from TrainEntity te where te.source=? and te.destination=?";
			Query query=session.createQuery(hql);
			query.setString(0, train.getSource());
			query.setString(1, train.getDestination());
			List<TrainEntity> teList=query.list();
			if(teList!=null){
				resultList=new ArrayList<>();
				for(TrainEntity te:teList){
					Train temp=new Train();
					temp.setArrival(te.getArrival());
					temp.setDestination(train.getDestination());
					temp.setDeparture(te.getDeparture());
					temp.setSource(train.getSource());
					temp.setTrainNumber(te.getTrainNumber());
					temp.setTrainName(te.getTrainName());
					temp.setJourneyDate(train.getJourneyDate());
	    			temp.setQuantity(train.getQuantity());
	    			temp.setTravelClass(train.getTravelClass());
	    			TrainJson bookingData=Factory.createTrainService().filterBookingData(train.getJourneyDate(),te.getBookingData());
	    			if(bookingData==null){
	    				throw new Exception("Train.BOOKING_NOT_STARTED");
	    			}
	    			temp.setBookingDate(bookingData.getBookingDate());
	    			if(train.getTravelClass().equals("Sleeper") && bookingData.getSleeper()>=train.getQuantity()){
	    				temp.setPrice(Train.SLEEPER);
	    				temp.setAvailableSeats(bookingData.getSleeper());
	    				resultList.add(temp);
	    			}
	    			else if(train.getTravelClass().equals("ThirdAC") && bookingData.getThirdAC()>=train.getQuantity()){
	    				temp.setPrice(Train.THIRDAC);
	    				temp.setAvailableSeats(bookingData.getThirdAC());
	    				resultList.add(temp);
	    			}
	    			else if(train.getTravelClass().equals("SecondAC") && bookingData.getSecondAC()>=train.getQuantity()){
	    				temp.setPrice(Train.SECONDAC);
	    				temp.setAvailableSeats(bookingData.getSecondAC());
	    				resultList.add(temp);
	    			}
	    			else if(train.getTravelClass().equals("FirstAC") && bookingData.getFirstAC()>=train.getQuantity()){
	    				temp.setPrice(Train.FIRSTAC);
	    				temp.setAvailableSeats(bookingData.getFirstAC());
	    				resultList.add(temp);
	    			}
	    			else{
	    				
	    			}
	    			if(resultList.size()==0){
	    				throw new Exception("Train.NO_TRAINS");
	    			}
				}
			}
			else
				throw new Exception("Train.NO_TRAINS");
		}
		catch(Exception e){
			e.printStackTrace();
			Logger logger=Logger.getLogger(this.getClass());
			logger.error(e.getMessage(),e);
			if(e.getMessage().contains("Train"))
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
	
	public Boolean book(Train train) throws Exception{
		SessionFactory factory=null;
		Session session=null;
		Boolean result=false;
		try{
			factory=HibernateUtility.createSessionFactory();
			session=factory.openSession();
			TrainEntity te=(TrainEntity)session.get(TrainEntity.class, train.getTrainNumber());
			String newBookingData=Factory.createTrainService().updateSeats(train.getJourneyDate(),te.getBookingData(),train.getTravelClass(),train.getQuantity());
			session.getTransaction().begin();
			te.setBookingData(newBookingData);
			session.getTransaction().commit();
			result=true;
		}
		catch(Exception e){
			Logger logger =Logger.getLogger(this.getClass());
			logger.error(e.getMessage(),e);
			if(e.getMessage().contains("Train"))
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
