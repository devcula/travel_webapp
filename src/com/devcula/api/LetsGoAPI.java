package com.devcula.api;

import java.lang.reflect.Type;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.devcula.bean.Booking;
import com.devcula.bean.ChangePassword;
import com.devcula.bean.Hotel;
import com.devcula.bean.Login;
import com.devcula.bean.User;
import com.devcula.json.FlightJson;
import com.devcula.resources.AppConfig;
import com.devcula.resources.Factory;
import com.devcula.resources.JSONParser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


@Path("LetsGo")
public class LetsGoAPI {
	@Path("register")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registerUser(String receivedData) throws Exception{
		Response response=null;
		User user=null;
		try{
			System.out.println(receivedData);
			user=JSONParser.fromJson(receivedData, User.class);
			Gson gson=new Gson();
			Type type=new TypeToken<User>() {}.getType();
			user=gson.fromJson(receivedData, type);
			String successMessage=Factory.createUserService().registerUser(user);
			user=new User();
			user.setMessage(successMessage);
			response=Response.status(Status.OK).entity(JSONParser.toJson(user)).build();
		}
		catch(Exception e){
			e.printStackTrace();
			String errorMessage=AppConfig.PROPERTIES.getProperty(e.getMessage());
			user=new User();
			user.setMessage(errorMessage);
			response=Response.status(Status.BAD_REQUEST).entity(JSONParser.toJson(user)).build();
		}
		return response;
	}
	
	@Path("login")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(String receivedData) throws Exception{
		Response response=null;
		Login loginBean=null;
		try{
			loginBean=JSONParser.fromJson(receivedData, Login.class);
			loginBean=Factory.createUserService().login(loginBean);
			String successMessage="Successfully Logged in.";
			loginBean.setMessage(successMessage);
			response=Response.status(Status.OK).entity(JSONParser.toJson(loginBean)).build();
		}
		catch(Exception e){
			String errorMessage=AppConfig.PROPERTIES.getProperty(e.getMessage());
			loginBean=new Login();
			loginBean.setMessage(errorMessage);
			response=Response.status(Status.BAD_REQUEST).entity(JSONParser.toJson(loginBean)).build();
		}
		return response;
	}
	
	@Path("fetchUserDetails")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser(String receivedData) throws Exception{
		Response response=null;
		User user=null;
		try{
			user=JSONParser.fromJson(receivedData, User.class);
			user=Factory.createUserService().getUser(user);
			response=Response.status(Status.OK).entity(JSONParser.toJson(user)).build();
		}
		catch(Exception e){
			String errorMessage=AppConfig.PROPERTIES.getProperty(e.getMessage());
			user.setEmailId(null);
			user.setMessage(errorMessage);
			response=Response.status(Status.BAD_REQUEST).entity(JSONParser.toJson(user)).build();
		}
		return response;
	}
	
	@Path("changePassword")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response changePassword(String receivedData) throws Exception{
		Response response=null;
		ChangePassword bean=null;
		try{
			bean=JSONParser.fromJson(receivedData, ChangePassword.class);
			String successMessage=Factory.createUserService().changePassword(bean);
			bean=new ChangePassword();
			bean.setMessage(successMessage);
			response=Response.status(Status.OK).entity(JSONParser.toJson(bean)).build();
		}
		catch(Exception e){
			String errorMessage=AppConfig.PROPERTIES.getProperty(e.getMessage());
			bean=new ChangePassword();
			bean.setMessage(errorMessage);
			response=Response.status(Status.BAD_REQUEST).entity(JSONParser.toJson(bean)).build();
		}
		return response;
	}
	
	@Path("fetchBookings")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response fetchUserBookings(String receivedData) throws Exception{
		Response response=null;
		List<Booking> bookings=null;
		try{
			User user=JSONParser.fromJson(receivedData, User.class);
			bookings=Factory.createBookService().fetchUserBookings(user.getEmailId());
			response=Response.status(Status.OK).entity(JSONParser.toJson(bookings)).build();
		}
		catch(Exception e){
			String errorMessage=AppConfig.PROPERTIES.getProperty(e.getMessage());
			Login loginBean=new Login();
			loginBean.setMessage(errorMessage);
			response=Response.status(Status.BAD_REQUEST).entity(JSONParser.toJson(loginBean)).build();
		}
		return response;
	}
	
	@Path("updateProfile")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateProfile(String receivedData) throws Exception{
		Response response=null;
		User user=null;
		try{
			user=JSONParser.fromJson(receivedData, User.class);
			String successMessage=Factory.createUserService().updateProfile(user);
			user=new User();
			user.setMessage(successMessage);
			response=Response.status(Status.OK).entity(JSONParser.toJson(user)).build();
		}
		catch(Exception e){
			String errorMessage=AppConfig.PROPERTIES.getProperty(e.getMessage());
			user=new User();
			user.setMessage(errorMessage);
			response=Response.status(Status.BAD_REQUEST).entity(JSONParser.toJson(user)).build();
		}
		return response;
	}
	
	@Path("authenticate")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response authenticateUser(String receivedData) throws Exception{
		Response response=null;
		Login login=null;
		try{
			login=JSONParser.fromJson(receivedData, Login.class);
			login=Factory.createUserService().authenticateUser(login);
			response=Response.status(Status.OK).entity(JSONParser.toJson(login)).build();
		}
		catch(Exception e){
			e.printStackTrace();
			String errorMessage=AppConfig.PROPERTIES.getProperty(e.getMessage());
			login=new Login();
			login.setMessage(errorMessage);
			response=Response.status(Status.SERVICE_UNAVAILABLE).entity(JSONParser.toJson(login)).build();
		}
		return response;
	}
	
	@Path("saveFeedback")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveFeedback(String receivedData) throws Exception{
		Response response=null;
		try{
			Booking booking=JSONParser.fromJson(receivedData, Booking.class);
			Factory.createBookService().saveFeedback(booking);
			Login login=new Login();
			login.setMessage("Thank you very much for your precious feedback");
			response=Response.status(Status.OK).entity(JSONParser.toJson(login)).build();
		}
		catch(Exception e){
			String errorMessage=AppConfig.PROPERTIES.getProperty(e.getMessage());
			Login login=new Login();
			login.setMessage(errorMessage);
			response=Response.status(Status.SERVICE_UNAVAILABLE).entity(JSONParser.toJson(login)).build();
		}
		return response;
	}
	
	@Path("forgotPassword")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response forgotPassword(String receivedData) throws Exception{
		Response response=null;
		Login login=null;
		try{
			login=JSONParser.fromJson(receivedData, Login.class);
			User user=Factory.createUserService().findEmailId(login.getEmailId());
			response=Response.status(Status.OK).entity(JSONParser.toJson(user)).build();
		}
		catch(Exception e){
			String errorMessage=AppConfig.PROPERTIES.getProperty(e.getMessage());
			login=new Login();
			login.setMessage(errorMessage);
			response=Response.status(Status.SERVICE_UNAVAILABLE).entity(JSONParser.toJson(login)).build();
		}
		return response;
	}
	
	@Path("resetPassword")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response resetPassword(String receivedData) throws Exception{
		Response response=null;
		User user=null;
		try{
			user=JSONParser.fromJson(receivedData, User.class);
			Factory.createUserService().resetPassword(user);
			response=Response.status(Status.OK).build();
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
	
	@Path("checkHotelBookings")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response checkHotelBookings(String receivedData) throws Exception{
		Response response=null;
		Hotel hotel=null;
		try{
			User user=JSONParser.fromJson(receivedData, User.class);
			hotel=Factory.createBookService().checkHotelBookings(user);
			response=Response.status(Status.OK).entity(JSONParser.toJson(hotel)).build();
			System.out.println(JSONParser.toJson(hotel));
		}
		catch(Exception e){
			//e.printStackTrace();
			String errorMessage=e.getMessage();
			Login login=new Login();
			login.setMessage(errorMessage);
			response=Response.status(Status.SERVICE_UNAVAILABLE).entity(JSONParser.toJson(login)).build();
		}
		return response;
	}
	
	@Path("submitHotelRating")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response submitHotelRating(String receivedData) throws Exception{
		Response response=null;
		try{
			System.out.println(receivedData);
			Hotel hotel=JSONParser.fromJson(receivedData, Hotel.class);
			String successMessage=Factory.createBookService().submitHotelRating(hotel);
			hotel=new Hotel();
			hotel.setMessage(successMessage);
			response=Response.status(Status.OK).entity(JSONParser.toJson(hotel)).build();
		}
		catch(Exception e){
			String errorMessage=e.getMessage();
			Login login=new Login();
			login.setMessage(errorMessage);
			response=Response.status(Status.SERVICE_UNAVAILABLE).entity(JSONParser.toJson(login)).build();
		}
		return response;
	}
	
	@Path("cancel")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response cancelBooking(String receivedData) throws Exception{
		Response response=null;
		try{
			Booking booking=JSONParser.fromJson(receivedData, Booking.class);
			Factory.createBookService().cancelBooking(booking);
			response=Response.status(Status.OK).build();
		}
		catch(Exception e){
			String errorMessage=AppConfig.PROPERTIES.getProperty(e.getMessage());
			Login login=new Login();
			login.setMessage(errorMessage);
			response=Response.status(Status.BAD_REQUEST).entity(JSONParser.toJson(login)).build();
		}
		return response;
	}
}

























