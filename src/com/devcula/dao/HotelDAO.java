package com.devcula.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.devcula.bean.Hotel;
import com.devcula.entity.HotelEntity;
import com.devcula.resources.Factory;
import com.devcula.resources.HibernateUtility;

public class HotelDAO {
	public List<Hotel> checkAvailability(Hotel hotel) throws Exception{
		SessionFactory factory=null;
		Session session=null;
		List<Hotel> resultList=null; 
		Calendar tempDate=hotel.getCheckInDate();
		try{
			factory=HibernateUtility.createSessionFactory();
			session=factory.openSession();
			String hql="Select he from HotelEntity he where he.city=? and he.roomsType=? order by he.rating desc";
			Query query=session.createQuery(hql);
			query.setString(0, hotel.getCity());
			query.setString(1, hotel.getRoomsType());
			List<HotelEntity> heList=query.list();
			if(heList!=null){
				resultList=new ArrayList<>();
				for(HotelEntity he:heList){
					//System.out.println("In dao before checking booking data: check in date: "+hotel.getCheckInDate().get(Calendar.DAY_OF_MONTH));
					Boolean available=Factory.createHotelService().checkBookingData(hotel.getCheckInDate(),hotel.getCheckOutDate(),he.getBookingData(),hotel.getQuantity());
					//System.out.println("In dao after checking booking data: check in date: "+hotel.getCheckInDate().get(Calendar.DAY_OF_MONTH));
					if(available){
						Hotel temp=new Hotel();
						//System.out.println("In dao: tempdate"+tempDate.get(Calendar.DAY_OF_MONTH));
						temp.setCheckInDate(tempDate);
						temp.setCheckOutDate(hotel.getCheckOutDate());
						//System.out.println("check in"+temp.getCheckInDate().get(Calendar.DAY_OF_MONTH));
						temp.setCity(hotel.getCity());
						temp.setHotelId(he.getHotelId());
						temp.setHotelName(he.getHotelName());
						if(hotel.getRoomsType().equals("standard"))
							temp.setPrice(Hotel.STANDARD);
						else if(hotel.getRoomsType().equals("deluxe"))
							temp.setPrice(Hotel.DELUXE);
						else{
							
						}
						temp.setQuantity(hotel.getQuantity());
						temp.setRating(he.getRating());
						temp.setRoomsType(hotel.getRoomsType());
						temp.setTimesRated(he.getTimesRated());
						resultList.add(temp);
					}
				}
			}
			if(resultList.size()==0)
				throw new Exception("Hotel.NO_HOTELS");
		}
		catch(Exception e){
			e.printStackTrace();
			Logger logger=Logger.getLogger(this.getClass());
			logger.error(e.getMessage(),e);
			if(e.getMessage().contains("Hotel"))
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
	public Boolean book(Hotel hotel) throws Exception{
		SessionFactory factory=null;
		Session session=null;
		Boolean result=false;
		try{
			factory=HibernateUtility.createSessionFactory();
			session=factory.openSession();
			HotelEntity he=(HotelEntity)session.get(HotelEntity.class, hotel.getHotelId());
			String newBookingData=Factory.createHotelService().updateRooms(hotel.getCheckInDate(),hotel.getCheckOutDate(),he.getBookingData(),hotel.getQuantity());
			session.getTransaction().begin();
			he.setBookingData(newBookingData);
			session.getTransaction().commit();
			result=true;
		}
		catch(Exception e){
			Logger logger =Logger.getLogger(this.getClass());
			logger.error(e.getMessage(),e);
			if(e.getMessage().contains("Hotel"))
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
	
	public Hotel findHotels(Hotel hotel) throws Exception{
		SessionFactory factory=null;
		Session session=null;
		try{
			factory=HibernateUtility.createSessionFactory();
			session=factory.openSession();
			String hql="Select count(*) from HotelEntity he where he.city=? and he.roomsType=?";
			Query query=session.createQuery(hql);
			query.setString(0, hotel.getCity());
			query.setString(1, "standard");
			hotel.setHotelId(((Long)query.uniqueResult()).intValue());
			query.setString(1, "deluxe");
			hotel.setQuantity(((Long)query.uniqueResult()).intValue());
			System.out.println(hotel.getHotelId());
			System.out.println(hotel.getQuantity());
			if(hotel.getHotelId()==0 && hotel.getQuantity()==0)
				throw new Exception("Hotel.NO_HOTELS");
		}
		catch(Exception e){
			e.printStackTrace();
			Logger logger =Logger.getLogger(this.getClass());
			logger.error(e.getMessage(),e);
			if(e.getMessage().contains("Hotel"))
				throw e;
			else
				throw new Exception("DAO.TECHNICAL_ERROR");
		}
		finally{
			if(session.isOpen() || session!=null)
				session.close();
		}
		return hotel;
	}
}
