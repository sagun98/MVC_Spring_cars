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

public class carDao {
	private Connection connection;
	
	public CarDao() {
		connection = DbUtil.getConnection();
	}
	
	public void addCar(Car car) {
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
}
