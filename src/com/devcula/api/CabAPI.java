package com.devcula.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.devcula.bean.Book;
import com.devcula.bean.Booking;
import com.devcula.bean.Cab;
import com.devcula.bean.Hotel;
import com.devcula.bean.Login;
import com.devcula.resources.AppConfig;
import com.devcula.resources.Factory;
import com.devcula.resources.JSONParser;

@Path("Cab")
public class CabAPI {

	@Path("fetchPlaces")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response fetchPlaces(String receivedData) throws Exception{
		Response response=null;
		try{
			Hotel hotel=JSONParser.fromJson(receivedData, Hotel.class);
			List<String> list=Factory.createCabService().fetchPlaces(hotel);
			response=Response.status(Status.OK).entity(JSONParser.toJson(list)).build();
		}
		catch(Exception e){
			String errorMessage=AppConfig.PROPERTIES.getProperty(e.getMessage());
			Login login=new Login();
			login.setMessage(errorMessage);
			response=Response.status(Status.SERVICE_UNAVAILABLE).entity(JSONParser.toJson(login)).build();
		}
		return response;
	}
	
	  @Path("checkAvailability")
	  @POST
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)
	  public Response checkAvailability(String receivedData) throws Exception{
	    Response response=null;
	    Cab cab=null;
	    List<Cab> list=null;
	    try{
	    	//System.out.println(receivedData);
	      cab=JSONParser.fromJson(receivedData,Cab.class);
	      list=Factory.createCabService().checkAvailability(cab);
	      response=Response.status(Status.OK).entity(JSONParser.toJson(list)).build();
	    }
	    catch(Exception e){
	      String errorMessage=AppConfig.PROPERTIES.getProperty(e.getMessage());
	      Login login=new Login();
	      login.setMessage(errorMessage);
	      response=Response.status(Status.SERVICE_UNAVAILABLE).entity(JSONParser.toJson(login)).build();
	    }
	    return response;
	  }
	  
	    @Path("book")
		@POST
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public Response bookCab(String receivedData) throws Exception{
		    Response response=null;
		    Booking booking=null;
		    try{
		      Book book=JSONParser.fromJson(receivedData,Book.class);
		      booking=Factory.createBookService().processBooking(book);
		      response=Response.status(Status.OK).entity(JSONParser.toJson(booking)).build();
		    }
		    catch(Exception e)
		    {
		    	e.printStackTrace();
		      String errorMessage=AppConfig.PROPERTIES.getProperty(e.getMessage());
		      Login login=new Login();
		      login.setMessage(errorMessage);
		      response=Response.status(Status.SERVICE_UNAVAILABLE).entity(JSONParser.toJson(login)).build();
		    }
		    return response;
		  }
}
