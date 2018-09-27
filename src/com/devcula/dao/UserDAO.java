package com.devcula.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.devcula.bean.ChangePassword;
import com.devcula.bean.Login;
import com.devcula.bean.User;
import com.devcula.entity.UserEntity;
import com.devcula.resources.HibernateUtility;
import com.devcula.validator.Validator;

public class UserDAO {

	public User findEmailId(String emailId) throws Exception{
		SessionFactory factory=null;
		Session session=null;
		User user=null;
		try{
			factory=HibernateUtility.createSessionFactory();
			session=factory.openSession();
			UserEntity ue=(UserEntity) session.get(UserEntity.class,emailId);
			if(ue!=null){
				user=new User();
				user.setAnswer(ue.getAnswer());
				switch (ue.getQuestion()) {
				case "1":
					user.setQuestion("What is your first pet's name?");
					break;
				case "2":
					user.setQuestion("In what year was your mother born?");
					break;
				case "3":
					user.setQuestion("What primary school did you attend?");
					break;
				case "4":
					user.setQuestion("In what town or city did you meet your spouse/partner?");
					break;
				case "5":
					user.setQuestion("What is the name of your favorite cousin?");
					break;
				case "6":
					user.setQuestion("What is your oldest sibling's birthday month and year?");
					break;
				case "7":
					user.setQuestion("Which phone number do you remember most from your childhood?");
					break;
				case "8":
					user.setQuestion("Which is your favorite web browser?");
					break;
				case "9":
					user.setQuestion("What was the make of your first car?");
					break;
				default:
					break;
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
			Logger logger=Logger.getLogger(this.getClass());
			logger.error(e.getMessage(),e);
			if(e.getMessage().contains("Login"))
				throw e;
			else
				throw new Exception("DAO.TECHNICAL_ERROR");
		}
		finally{
			if(session.isOpen() || session!=null)
				session.close();
		}
		return user;
	}
	
	public String registerUser(User user) throws Exception{
		SessionFactory factory=null;
		Session session=null;
		String result=null;
		try{
			factory=HibernateUtility.createSessionFactory();
			session=factory.openSession();
			UserEntity ue=new UserEntity();
			ue.setContactNo(user.getContactNo());
			ue.setDateOfBirth(user.getDateOfBirth());
			ue.setEmailId(user.getEmailId().toLowerCase());
			ue.setFirstName(user.getFirstName());
			ue.setGender(user.getGender());
			ue.setLastName(user.getLastName());
			ue.setPassword(user.getPassword());
			ue.setQuestion(user.getQuestion());
			ue.setAnswer(user.getAnswer());
			session.getTransaction().begin();
			session.persist(ue);
			session.getTransaction().commit();
			result="You have registered successfully. Kindly login to continue";
		}
		catch(Exception e){
			Logger logger=Logger.getLogger(this.getClass());
			logger.error(e.getMessage(),e);
			throw new Exception("DAO.TECHNICAL_ERROR");
		}
		finally{
			if(session.isOpen() || session!=null)
				session.close();
		}
		return result;
	}
	
	public Login login(Login loginBean) throws Exception{
		SessionFactory factory=null;
		Session session=null;
		try{
			factory=HibernateUtility.createSessionFactory();
			session=factory.openSession();
			UserEntity ue=(UserEntity)session.get(UserEntity.class, loginBean.getEmailId());
			if(ue==null)
				throw new Exception("Login.INVALID_EMAIL");
			else{
				if(ue.getPassword().equals(loginBean.getPassword())){
					loginBean.setName(ue.getFirstName());
					String combo=ue.getEmailId()+ue.getPassword();
					Validator validator=new Validator();
					loginBean.setHash(validator.generateHash(combo));
				}
				else
					throw new Exception("Login.INVALID_PASSWORD");
			}
		}
		catch(Exception e){
			Logger logger=Logger.getLogger(this.getClass());
			logger.error(e.getMessage(),e);
			if(e.getMessage().contains("Login"))
				throw e;
			else
				throw new Exception("DAO.TECHNICAL_ERROR");
		}
		finally{
			if(session.isOpen() || session!=null)
				session.close();
		}
		return loginBean;
	}
	
	public User getUser(User user) throws Exception{
		SessionFactory factory=null;
		Session session=null;
		try{
			factory=HibernateUtility.createSessionFactory();
			session=factory.openSession();
			UserEntity ue=(UserEntity)session.get(UserEntity.class,user.getEmailId());
			if(ue!=null)
			{
				user.setContactNo(ue.getContactNo());
				user.setDateOfBirth(ue.getDateOfBirth());
				user.setFirstName(ue.getFirstName());
				user.setGender(ue.getGender());
				user.setLastName(ue.getLastName());
			}
			else
			{
				throw new Exception("Login.USER_NOT_FOUND");
			}
		}
		catch(Exception e){
			Logger logger=Logger.getLogger(this.getClass());
			logger.error(e.getMessage(),e);
			if(e.getMessage().contains("Login"))
				throw e;
			else
				throw new Exception("DAO.TECHNICAL_ERROR");
		}
		finally{
			if(session.isOpen() || session!=null)
				session.close();
		}
		return user;
	}
	
	public String changePassword(ChangePassword bean) throws Exception{
		SessionFactory factory=null;
		Session session=null;
		String result=null;
		try{
			factory=HibernateUtility.createSessionFactory();
			session=factory.openSession();
			UserEntity ue=(UserEntity)session.get(UserEntity.class,bean.getEmailId());
			if(ue!=null){
				if(bean.getOldPassword().equals(ue.getPassword())){
					session.getTransaction().begin();
					ue.setPassword(bean.getNewPassword());
					session.getTransaction().commit();
					result="Password changed successfully.";
				}
				else
					throw new Exception("Invalid.OLD_PASSWORD");
			}
			else
				throw new Exception("Login.USER_NOT_FOUND");
		}
		catch(Exception e){
			Logger logger=Logger.getLogger(this.getClass());
			logger.error(e.getMessage(),e);
			if(e.getMessage().contains("Login") || e.getMessage().contains("Invalid"))
				throw e;
			else
				throw new Exception("DAO.TECHNICAL_ERROR");
		}
		finally{
			if(session.isOpen() || session!=null)
				session.close();
		}
		return result;
	}
	
	public String updateProfile(User user) throws Exception{
		SessionFactory factory=null;
		Session session=null;
		String result=null;
		try{
			factory=HibernateUtility.createSessionFactory();
			session=factory.openSession();
			UserEntity ue=(UserEntity)session.get(UserEntity.class, user.getEmailId());
			if(ue!=null){
				if(ue.getPassword().equals(user.getPassword())){
					session.getTransaction().begin();
					ue.setFirstName(user.getFirstName());
					ue.setLastName(user.getLastName());
					ue.setContactNo(user.getContactNo());
					session.getTransaction().commit();
					result="Profile Updated Successfully";
				}
				else
					throw new Exception("Login.INVALID_PASSWORD");
			}
			else
				throw new Exception("Login.USER_NOT_FOUND");
		}
		catch(Exception e){
			Logger logger=Logger.getLogger(this.getClass());
			logger.error(e.getMessage(),e);
			if(e.getMessage().contains("Login"))
				throw e;
			else
				throw new Exception("DAO.TECHNICAL_ERROR");
		}
		finally{
			if(session.isOpen() || session!=null)
				session.close();
		}
		return result;
	}
	
	public Login authenticateUser(Login login) throws Exception{
		SessionFactory factory=null;
		Session session=null;
		try{
			factory=HibernateUtility.createSessionFactory();
			session=factory.openSession();
			UserEntity ue=(UserEntity)session.get(UserEntity.class, login.getEmailId());
			if(ue!=null){
				String value=login.getEmailId()+ue.getPassword();
				Validator validator=new Validator();
				if(login.getHash().equals(validator.generateHash(value))){
					login.setMessage(validator.generateHash(value));
					login.setName(ue.getFirstName());
				}
			}
		}
		catch(Exception e){
			Logger logger=Logger.getLogger(this.getClass());
			logger.error(e.getMessage(),e);
			throw new Exception("DAO.TECHNICAL_ERROR");
		}
		finally{
			if(session.isOpen() || session!=null)
				session.close();
		}
		return login;
	}
	
	public void resetPassword(User user) throws Exception{
		SessionFactory factory=null;
		Session session=null;
		try{
			factory=HibernateUtility.createSessionFactory();
			session=factory.openSession();
			UserEntity ue=(UserEntity)session.get(UserEntity.class, user.getEmailId());
			if(ue!=null){
				if(ue.getAnswer().equals(user.getAnswer())){
					session.getTransaction().begin();
					ue.setPassword(new Validator().generateHash(user.getPassword()));
					session.getTransaction().commit();
				}
				else
					throw new Exception("Login.SMART");
			}
			else
				throw new Exception("Login.INVALID_EMAIL");
		}
		catch(Exception e){
			e.printStackTrace();
			Logger logger=Logger.getLogger(this.getClass());
			logger.error(e.getMessage(),e);
			if(e.getMessage().contains("Login"))
				throw e;
			else
				throw new Exception("DAO.TECHNICAL_ERROR");
		}
		finally{
			if(session.isOpen() || session!=null)
				session.close();
		}
	}
}

















