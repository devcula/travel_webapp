package com.devcula.validator;

import java.security.MessageDigest;
import java.util.Calendar;

import org.apache.log4j.Logger;

import com.devcula.bean.User;
import com.devcula.resources.Factory;

public class Validator {
	
	public void isValidEmailId(String emailId) throws Exception{
		try{
			User temp=Factory.createUserService().findEmailId(emailId);
			if(temp!=null)
				throw new Exception("Validator.EMAIL_EXISTS");
		}
		catch(Exception e){
			Logger logger=Logger.getLogger(this.getClass());
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	
	public void isValidDateOfBirth(Calendar dob) throws Exception{
		try{
			Calendar today=Calendar.getInstance();
			Long ms=today.getTimeInMillis()-dob.getTimeInMillis();
			ms=ms/(1000*60*60*24*365);
			if(ms<13)
				throw new Exception("Validator.INVALID_AGE");
		}
		catch(Exception e){
			Logger logger=Logger.getLogger(this.getClass());
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	
	public String generateHash(String input) throws Exception{
		String output=null;
		try{
			MessageDigest md = MessageDigest.getInstance("SHA-256");
	        md.update(input.getBytes());
	        byte byteData[] = md.digest();
	        StringBuffer hexString = new StringBuffer();
	    	for (int i=0;i<byteData.length;i++) {
	    		String hex=Integer.toHexString(0xff & byteData[i]);
	   	     	if(hex.length()==1) hexString.append('0');
	   	     	hexString.append(hex);
	    	}
	    	output=hexString.toString();
		}
		catch(Exception e){
			Logger logger=Logger.getLogger(this.getClass());
			logger.error(e.getMessage(),e);
			throw e;
		}
		return output;
	}
}
