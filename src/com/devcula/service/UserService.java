package com.devcula.service;

import org.apache.log4j.Logger;

import com.devcula.bean.ChangePassword;
import com.devcula.bean.Login;
import com.devcula.bean.User;
import com.devcula.resources.Factory;
import com.devcula.validator.Validator;

public class UserService {
	
	public User findEmailId(String emailId) throws Exception{
		try{
			return Factory.createUserDAO().findEmailId(emailId.toLowerCase());
		}
		catch(Exception e){
			Logger logger=Logger.getLogger(this.getClass());
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	
	public String registerUser(User user) throws Exception{
		try{
			Validator validator=new Validator();
			validator.isValidEmailId(user.getEmailId());
			validator.isValidDateOfBirth(user.getDateOfBirth());
			user.setPassword(validator.generateHash(user.getPassword()));
			return Factory.createUserDAO().registerUser(user);
		}
		catch(Exception e){
			Logger logger=Logger.getLogger(this.getClass());
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	
	public Login login(Login loginBean) throws Exception{
		try{
			loginBean.setEmailId(loginBean.getEmailId().toLowerCase());
			Validator validator=new Validator();
			loginBean.setPassword(validator.generateHash(loginBean.getPassword()));
			return Factory.createUserDAO().login(loginBean);
		}
		catch(Exception e){
			Logger logger=Logger.getLogger(this.getClass());
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	
	public User getUser(User user) throws Exception{
		try{
			user.setEmailId(user.getEmailId().toLowerCase());
			return Factory.createUserDAO().getUser(user);
		}
		catch(Exception e){
			Logger logger=Logger.getLogger(this.getClass());
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	
	public String changePassword(ChangePassword bean) throws Exception{
		try{
			bean.setEmailId(bean.getEmailId().toLowerCase());
			Validator validator=new Validator();
			bean.setOldPassword(validator.generateHash(bean.getOldPassword()));
			bean.setNewPassword(validator.generateHash(bean.getNewPassword()));
			return Factory.createUserDAO().changePassword(bean);
		}
		catch(Exception e){
			Logger logger=Logger.getLogger(this.getClass());
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	public String updateProfile(User user) throws Exception{
		try{
			user.setEmailId(user.getEmailId().toLowerCase());
			Validator validator=new Validator();
			user.setPassword(validator.generateHash(user.getPassword()));
			return Factory.createUserDAO().updateProfile(user);
		}
		catch(Exception e){
			Logger logger=Logger.getLogger(this.getClass());
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	public Login authenticateUser(Login login) throws Exception{
		try{
			return Factory.createUserDAO().authenticateUser(login);
		}
		catch(Exception e){
			Logger logger=Logger.getLogger(this.getClass());
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	
	public void resetPassword(User user) throws Exception{
		try{
			user.setEmailId(user.getEmailId().toLowerCase());
			Factory.createUserDAO().resetPassword(user);
		}
		catch(Exception e){
			Logger logger=Logger.getLogger(this.getClass());
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
}
