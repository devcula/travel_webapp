package com.devcula.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.devcula.bean.Login;
import com.devcula.bean.Payment;
import com.devcula.resources.AppConfig;
import com.devcula.resources.Factory;
import com.devcula.resources.JSONParser;


@Path("Payment")
public class PaymentAPI {
	
	@Path("pay")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response pay(String receivedData) throws Exception{
		Response response=null;
		Login login=null;
		try{
			//System.out.println(receivedData);
			Payment payment=JSONParser.fromJson(receivedData, Payment.class);
			String result=Factory.createPaymentService().performPayment(payment);
			login=new Login();
			login.setMessage(result);
			response=Response.status(Status.OK).entity(JSONParser.toJson(login)).build();
		}
		catch(Exception e){
			e.printStackTrace();
			String errorMessage=AppConfig.PROPERTIES.getProperty(e.getMessage());
			login=new Login();
			login.setMessage(errorMessage);
			response=Response.status(Status.BAD_REQUEST).entity(login).build();
		}
		return response;
	}
}
