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
import com.devcula.bean.Login;
import com.devcula.bean.Train;
import com.devcula.resources.AppConfig;
import com.devcula.resources.Factory;
import com.devcula.resources.JSONParser;

@Path("Train")
public class TrainAPI {

	@Path("checkAvailability")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response checkAvailability(String receivedData) throws Exception{
		System.out.println(receivedData);
		Response response=null;
		List<Train> list=null;
		Train train=null;
		try{
			train=JSONParser.fromJson(receivedData, Train.class);
			list=Factory.createTrainService().checkAvailability(train);
			response=Response.status(Status.OK).entity(JSONParser.toJson(list)).build();
		}
		catch(Exception e){
			String errorMessage=AppConfig.PROPERTIES.getProperty(e.getMessage());
			Login loginBean=new Login();
			loginBean.setMessage(errorMessage);
			response=Response.status(Status.BAD_REQUEST).entity(JSONParser.toJson(loginBean)).build();
		}
		return response;
	}
	
	@Path("book")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response bookTrain(String receivedData) throws Exception{
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
