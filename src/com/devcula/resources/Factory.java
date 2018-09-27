package com.devcula.resources;

import com.devcula.dao.BookDAO;
import com.devcula.dao.BusDAO;
import com.devcula.dao.CabDAO;
import com.devcula.dao.CityDAO;
import com.devcula.dao.FlightDAO;
import com.devcula.dao.HotelDAO;
import com.devcula.dao.PaymentDAO;
import com.devcula.dao.TrainDAO;
import com.devcula.dao.UserDAO;
import com.devcula.service.BookService;
import com.devcula.service.BusService;
import com.devcula.service.CabService;
import com.devcula.service.CityService;
import com.devcula.service.FlightService;
import com.devcula.service.HotelService;
import com.devcula.service.PaymentService;
import com.devcula.service.TrainService;
import com.devcula.service.UserService;

public class Factory {

	public static UserDAO createUserDAO(){
		return new UserDAO();
	}

	public static UserService createUserService(){
		return new UserService();
	}

	public static String getConnectionString(){
		return "jdbc:mysql://localhost:8889/letsgo";
	}

	public static CityService createCityService(){
		return new CityService();
	}

	public static CityDAO createCityDAO(){
		return new CityDAO();
	}

	public static FlightService createFlightService(){
		return new FlightService();
	}

	public static FlightDAO createFlightDAO(){
		return new FlightDAO();
	}

	public static BookService createBookService(){
		return new BookService();
	}

	public static BookDAO createBookDAO(){
		return new BookDAO();
	}
	
	public static TrainService createTrainService(){
		return new TrainService();
	}
	
	public static TrainDAO createTrainDAO(){
		return new TrainDAO();
	}
	
	public static BusService createBusService(){
		return new BusService();
	}
	
	public static BusDAO createBusDAO(){
		return new BusDAO();
	}
	
	public static HotelDAO createHotelDAO(){
		return new HotelDAO();
	}
	public static HotelService createHotelService(){
		return new HotelService();
	}
	public static PaymentService createPaymentService(){
		return new PaymentService();
	}
	public static PaymentDAO createPaymentDAO(){
		return new PaymentDAO();
	}
	public static CabDAO createCabDAO(){
		return new CabDAO();
	}
	public static CabService createCabService(){
		return new CabService();
	}
}
