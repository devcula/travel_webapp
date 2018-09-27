package com.devcula.dao;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.devcula.resources.HibernateUtility;

public class CityDAO{
	public static List<String> cities=null;
  
  public List<String> getCities() throws Exception{
	    List<String> list=null;
	    SessionFactory factory=null;
	    Session session=null;
	    if(CityDAO.cities==null){
	    	try{
		    	factory=HibernateUtility.createSessionFactory();
		    	session=factory.openSession();
		    	String hql="Select ce.city from CityEntity ce";
		    	Query query=session.createQuery(hql);
		    	list=query.list();
		      if(list.size()==0)
		        throw new Exception("City.NO_CITY_FOUND");
		      else
		    	  CityDAO.cities=list;
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
		      if(session.isOpen() || session!=null)
		    	  session.close();
		    }
		    return list;
	    }
	    else
	    	return CityDAO.cities;
	    
	  }
}
