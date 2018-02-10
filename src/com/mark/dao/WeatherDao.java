package com.mark.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.mark.model.WeatherSensorModel;

public class WeatherDao implements WeatherDaoInterface {

	// TODO: Switch to SQL injection
	@Override
	public void save(WeatherSensorModel model) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://jsand.b.hostable.com:3306/jsand_weather",
					"jsand", "dbs99arm");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("INSERT INTO READINGS (HUMIDITY, PRESSURE) VALUES (" + model.getHumidity() +", " + model.getPressure() + ")");
			System.out.println("Saved to database");
			con.close();
		} catch (Exception e) {
			System.out.println("Failed to save to database");
			e.printStackTrace();
		}
	}

	@Override
	public List<WeatherSensorModel> findAll() {
		List<WeatherSensorModel> list = new ArrayList<WeatherSensorModel>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://jsand.b.hostable.com:3306/jsand_weather",
					"jsand", "dbs99arm");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM emp");
			
			WeatherSensorModel model = new WeatherSensorModel();
			model.setSensorName("Main");
			while (rs.next()) {
				model.setHumidity(rs.getDouble(1));
				model.setPressure(rs.getDouble(2));
				
				list.add(model);
			}
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
