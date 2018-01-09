package com.abc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.abc.model.Car;
import com.abc.util.DbUtil;

public class CarDao {
	private Connection connection;
	
	public CarDao() {
		connection = DbUtil.getConnection();
	}
	
	public void addCar(Car Car) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("insert into cars(manufacturer, model, city, registrationNumber) values(?,?,?,?)");
			preparedStatement.setString(1, Car.getManufacturer());
			preparedStatement.setInt(2, Car.getModel());
			preparedStatement.setString(3, Car.getCity());
			preparedStatement.setString(4, Car.getRegistrationNumber());
			
			preparedStatement.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteCar(int CarId) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("delete from cars where carId=?");
			preparedStatement.setInt(1, CarId);
			preparedStatement.executeUpdate();
			}
		catch(SQLException e) {
			e.printStackTrace();
			}
		}
	
	public void updateCar(Car Car) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("update cars set manufacturer=?, model=?, city=?, registrationNumber=? where carId=?");
			preparedStatement.setString(1, Car.getManufacturer());
			preparedStatement.setInt(2, Car.getModel());
			preparedStatement.setString(3, Car.getCity());
			preparedStatement.setString(4, Car.getRegistrationNumber());
			preparedStatement.setInt(5, Car.getCarId());
			preparedStatement.executeUpdate();
			}
		catch(SQLException e) {
			e.printStackTrace();
			}
		}
	
	public List<Car> getAllCars(){
		List<Car> Cars = new ArrayList<Car>();
		try {
			Statement statement =connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from cars");
			while(rs.next()) {
				Car Car = new Car(); 
				Car.setCarId(rs.getInt("carId"));
				Car.setManufacturer(rs.getString("manufacturer"));
				Car.setModel(rs.getInt("model"));
				Car.setCity(rs.getString("city"));
				Car.setRegistrationNumber(rs.getString("registrationNumber"));
				Cars.add(Car);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return Cars;
		}
	
	public Car getCarById(int CarId) {
		Car Car = new Car();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select * from cars where carId=?");
			preparedStatement.setInt(1, CarId);
			ResultSet rs = preparedStatement.executeQuery();
			
			if (rs.next()) {
				Car.setCarId(rs.getInt("carId"));
				Car.setManufacturer(rs.getString("manufacturer"));
				Car.setModel(rs.getInt("model"));
				Car.setCity(rs.getString("city"));
				Car.setRegistrationNumber(rs.getString("registrationNumber"));
				}
			}
		catch(SQLException e) {
			e.printStackTrace();
			}
		return Car;
		
		}
	}
