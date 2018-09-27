package com.devcula.api;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

import javax.ws.rs.core.Response.Status;

import com.devcula.bean.Book;
import com.devcula.bean.Booking;
import com.devcula.bean.Flight;
import com.devcula.bean.Login;
import com.devcula.resources.AppConfig;
import com.devcula.resources.Factory;
import com.devcula.resources.JSONParser;

@Path("Flight")
public class FlightAPI{

  @Path("checkAvailability")
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response checkAvailability(String receivedData) throws Exception{
    Response response=null;
    Flight flight=null;
    List<Flight> list=null;
    try{
      flight=JSONParser.fromJson(receivedData,Flight.class);
      list=Factory.createFlightService().checkAvailability(flight);
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
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response bookFlight(String receivedData) throws Exception{
    Response response=null;
    Booking booking=null;
    try{
      Book book=JSONParser.fromJson(receivedData,Book.class);
      booking=Factory.createBookService().processBooking(book);
      response=Response.status(Status.OK).entity(JSONParser.toJson(booking)).build();
    }
    catch(Exception e)
    {
      String errorMessage=AppConfig.PROPERTIES.getProperty(e.getMessage());
      Login login=new Login();
      login.setMessage(errorMessage);
      response=Response.status(Status.SERVICE_UNAVAILABLE).entity(JSONParser.toJson(login)).build();
    }
    return response;
  }
}
