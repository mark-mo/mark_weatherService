package com.mark.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;

import com.mark.beans.WeatherSensorModel;
import com.mark.exception.DatabaseErrorException;

@Stateless
@Local(DataAccessInterface.class)
@LocalBean
@Named
public class WeatherDAO implements DataAccessInterface<WeatherSensorModel> {
	private Connection con;

	public WeatherDAO() {
		// null at first, to be set later
		con = null;
	}

	public void makeConnection() {
		if (con == null) {
			// DB Connection Info
			con = null;
			String url = "jdbc:mysql://172.30.79.95:3306/Weather";
			String username = "weather";
			String password = "weathPiProject361";

			try {
				// Connect to database
				con = DriverManager.getConnection(url, username, password);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean create(WeatherSensorModel model) {
		makeConnection();

		try {
			// Query for # of rows with matching username and password
			String sql1 = String.format("INSERT INTO readings (HUMIDITY, PRESSURE, DATETIME) VALUES (%f, %f, '%s')",
					model.getHumidity(), model.getPressure(), model.getTime());
			Statement stmt1 = con.createStatement();
			stmt1.executeUpdate(sql1);
			stmt1.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseErrorException(e);
		} finally {
			// Cleanup Database
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new DatabaseErrorException(e);
				}
			}
		}
		return true;
	}

	// Finds past 10 entries
	@Override
	public List<WeatherSensorModel> findAll() {
		makeConnection();
		List<WeatherSensorModel> weatherList = new ArrayList<WeatherSensorModel>();

		try {
			String sql1 = "SELECT * FROM readings";
			Statement stmt1 = con.createStatement();
			ResultSet rs1 = stmt1.executeQuery(sql1);
			
			while (rs1.next()) {
				WeatherSensorModel weather = new WeatherSensorModel(rs1.getInt("HUMIDITY"), rs1.getInt("PRESSURE"),
						rs1.getString("DATETIME"));

				weatherList.add(weather);
			}
			rs1.close();
			stmt1.close();

			// return albums;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseErrorException(e);
		} finally {
			// Cleanup Database
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new DatabaseErrorException(e);
				}
			}
		}
		return weatherList;
	}

	@Override
	public WeatherSensorModel findByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WeatherSensorModel findBy(WeatherSensorModel t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(WeatherSensorModel t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(WeatherSensorModel t) {
		// TODO Auto-generated method stub
		return false;
	}
}
