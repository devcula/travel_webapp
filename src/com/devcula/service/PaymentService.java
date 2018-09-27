package com.devcula.service;

import org.apache.log4j.Logger;

import com.devcula.bean.Payment;
import com.devcula.resources.Factory;

public class PaymentService {

	public String performPayment(Payment payment) throws Exception{
		try{
			return Factory.createPaymentDAO().performPayment(payment);
		}
		catch(Exception e){
			Logger logger=Logger.getLogger(this.getClass());
			logger.error(e.getMessage(),e);
			throw e;
		}
	}
}
