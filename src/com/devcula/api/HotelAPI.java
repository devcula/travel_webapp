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
import com.devcula.bean.Hotel;
import com.devcula.bean.Login;
import com.devcula.resources.AppConfig;
import com.devcula.resources.Factory;
import com.devcula.resources.JSONParser;


@Path("Hotel")
public class HotelAPI {

	@Path("checkAvailability")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response checkAvailability(String receivedData) throws Exception{
		Response response=null;
		Hotel hotel=null;
		try{
			System.out.println(receivedData);
			hotel=JSONParser.fromJson(receivedData, Hotel.class);
			//System.out.println("In api: check in date: "+hotel.getCheckInDate().get(Calendar.DAY_OF_MONTH));
			List<Hotel> list=Factory.createHotelService().checkAvailability(hotel);
			response=Response.status(Status.OK).entity(JSONParser.toJson(list)).build();
			//System.out.println(JSONParser.toJson(list));
		}
		catch(Exception e){
			e.printStackTrace();
			String errorMessage=AppConfig.PROPERTIES.getProperty(e.getMessage());
			hotel=new Hotel();
			hotel.setMessage(errorMessage);
			response=Response.status(Status.SERVICE_UNAVAILABLE).entity(JSONParser.toJson(hotel)).build();
		}
		return response;
	}
	
	@Path("book")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response bookHotel(String receivedData) throws Exception{
		Response response=null;
		Booking booking=null;
		try{
			//System.out.println(receivedData);
			Book book=JSONParser.fromJson(receivedData, Book.class);
			booking=Factory.createBookService().processBooking(book);
			response=Response.status(Status.OK).entity(JSONParser.toJson(booking)).build();
		}
		catch(Exception e){
			e.printStackTrace();
		    String errorMessage=AppConfig.PROPERTIES.getProperty(e.getMessage());
		    Login login=new Login();
		    login.setMessage(errorMessage);
		    response=Response.status(Status.SERVICE_UNAVAILABLE).entity(JSONParser.toJson(login)).build();
		}
		return response;
	}
	
	@Path("find")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response findHotels(String receivedData) throws Exception{
		Response response=null;
		try{
			System.out.println(receivedData);
			Hotel hotel=JSONParser.fromJson(receivedData, Hotel.class);
			hotel=Factory.createHotelService().findHotels(hotel);
			response=Response.status(Status.OK).entity(JSONParser.toJson(hotel)).build();
		}
		catch(Exception e){
			String errorMessage=AppConfig.PROPERTIES.getProperty(e.getMessage());
			Login login=new Login();
			login.setMessage(errorMessage);
			response=Response.status(Status.SERVICE_UNAVAILABLE).entity(JSONParser.toJson(login)).build();
		}
		return response;
	}
}
