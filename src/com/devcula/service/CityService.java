package com.devcula.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.devcula.resources.Factory;

public class CityService{
  public List<String> getCities() throws Exception{
    try{
      return Factory.createCityDAO().getCities();
    }
    catch(Exception e){
      Logger logger=Logger.getLogger(this.getClass());
      logger.error(e.getMessage(),e);
      throw e;
    }
  }
}
