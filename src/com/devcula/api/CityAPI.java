package com.devcula.api;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import java.util.List;

import com.devcula.bean.Login;
import com.devcula.resources.AppConfig;
import com.devcula.resources.Factory;
import com.devcula.resources.JSONParser;

@Path("City")
public class CityAPI{
  @Path("getCities")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getCities() throws Exception{
    Response response = null;
    try{
      List<String> cityList=Factory.createCityService().getCities();
      response=Response.status(Status.OK).entity(JSONParser.toJson(cityList)).build();
    } 
    catch(Exception e){
      String errorMessage=AppConfig.PROPERTIES.getProperty(e.getMessage());
      Login bean=new Login();
      bean.setMessage(errorMessage);
      response=Response.status(Status.SERVICE_UNAVAILABLE).entity(JSONParser.toJson(bean)).build();
    }
    return response;
  }
}
