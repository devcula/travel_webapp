package com.devcula.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.devcula.bean.Payment;
import com.devcula.entity.PaymentEntity;
import com.devcula.resources.HibernateUtility;

public class PaymentDAO {

	public String performPayment(Payment payment) throws Exception{
		SessionFactory factory=null;
		Session session=null;
		String result=null;
		try{
			factory=HibernateUtility.createSessionFactory();
			session=factory.openSession();
			PaymentEntity pe=(PaymentEntity)session.get(PaymentEntity.class,payment.getCardNumber());
			if(pe!=null){
				if(pe.getAmount()>=payment.getAmount()){
					if(pe.getCardExpiryDate().equals(payment.getCardExpiryDate())){
						if(pe.getCardholderName().toLowerCase().equals(payment.getCardholderName().toLowerCase())){
							if(pe.getCardType().equals(payment.getCardType())){
								if(pe.getCvv().equals(payment.getCvv())){
									session.getTransaction().begin();
									pe.setAmount(pe.getAmount()-payment.getAmount());
									session.getTransaction().commit();
									result="success";
									return result;
								}
								throw new Exception("Payment.WRONG_CVV");
							}
							throw new Exception("Payment.WRONG_CARDTYPE");
						}
						throw new Exception("Payment.WRONG_CARDHOLDERNAME");
					}
					throw new Exception("Payment.WRONG_EXPIRYDATE");
				}
				throw new Exception("Payment.INSUFFICIENT_FUNDS");
			}
			else
				throw new Exception("Payment.WRONG_CARDNUMBER");
		}
		catch(Exception e){
			e.printStackTrace();
			Logger logger=Logger.getLogger(this.getClass());
			logger.error(e.getMessage(),e);
			if(e.getMessage().contains("Payment"))
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
