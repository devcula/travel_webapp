package com.devcula.resources;

import java.sql.*;

import com.devcula.json.BusJson;
import com.devcula.json.CabJson;
import com.devcula.json.FlightJson;
import com.devcula.json.HotelJson;
import com.devcula.json.TrainJson;

public class DataInit {
	
	public static void insertFlight(){
		String sql="Update flight set bookingData=?";
		try{
			Connection connection=DriverManager.getConnection(Factory.getConnectionString(),Factory.getDbUsername(),Factory.getDbPassword());
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, FlightJson.generateData(2));
			ps.executeUpdate();
			connection.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void insertTrain(){
		String sql="Update train set bookingData=?";
		try{
			Connection connection=DriverManager.getConnection(Factory.getConnectionString(),Factory.getDbUsername(),Factory.getDbPassword());
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, TrainJson.generateData(2));
			ps.executeUpdate();
			connection.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void insertBus(){
		String sql="Update bus set bookingData=?";
		try{
			Connection connection=DriverManager.getConnection(Factory.getConnectionString(),Factory.getDbUsername(),Factory.getDbPassword());
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, BusJson.generateData(2));
			ps.executeUpdate();
			connection.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void insertHotel(){
		String sql="Update hotel set bookingData=?";
		try{
			Connection connection=DriverManager.getConnection(Factory.getConnectionString(),Factory.getDbUsername(),Factory.getDbPassword());
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, HotelJson.generateData(2));
			ps.executeUpdate();
			connection.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void insertCab(){
		String sql="Update cab set bookingData=?";
		try{
			Connection connection=DriverManager.getConnection(Factory.getConnectionString(),Factory.getDbUsername(),Factory.getDbPassword());
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, CabJson.generateData(2));
			ps.executeUpdate();
			connection.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		insertFlight();
		insertTrain();
		insertBus();
		insertHotel();
		insertCab();
		String sql="select bookingData from train ";
		try{
//			Calendar today=Calendar.getInstance();
//			Connection connection=DriverManager.getConnection(Factory.getConnectionString());
//				PreparedStatement ps=connection.prepareStatement(sql);
//					ps.setDate(1, Date.valueOf(LocalDate.of(today.get(Calendar.YEAR), today.get(Calendar.MONTH)+1, today.get(Calendar.DAY_OF_MONTH))));
//					ResultSet rs=ps.executeQuery();
//					while(rs.next())
//						System.out.println("True");
//			String data=TrainJson.generateData(2);
//			System.out.println(data);
//			System.out.println(data.length());
//			data=BusJson.generateData(2);
//			System.out.println(data);
//			System.out.println(data.length());
//			data=FlightJson.generateData(2);
//			System.out.println(data);
//			System.out.println(data.length());
			Connection connection=DriverManager.getConnection(Factory.getConnectionString(),Factory.getDbUsername(),Factory.getDbPassword());
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
				System.out.println(rs.getString(1));
			connection.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
//		LocalDateTime now=LocalDateTime.now();
//		  String id=now.toString();
//		  String newId="";
//		  for(int i=0;i<id.length();i++){
//			  if(Character.isDigit(id.charAt(i)))
//				  newId+=id.charAt(i);
//		  }
//		  newId=newId.substring(4, newId.length());
//		  newId="T"+newId;
//		  System.out.println(newId);
	}
}
