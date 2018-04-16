package com.mark.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import com.mark.beans.WeatherSensorModel;
import com.mark.exception.DatabaseErrorException;

import com.mark.util.LoggingInterceptor;
import com.mark.util.LoggingInterface;
import com.mark.util.WeatherSensorFactory;

@Stateless
@Interceptors(LoggingInterceptor.class)
@Local(DataAccessInterface.class)
@LocalBean
public class WeatherDAO implements DataAccessInterface<WeatherSensorModel> {
	private Connection con;
	
	@Inject
	LoggingInterface logging;

	public WeatherDAO() {
		// null at first, to be set later
		con = null;
	}

	public void makeConnection() {
		logging.info("Entering WeatherDAO.makeConnection()");

		if (con == null) {
			logging.info("Creating a connection to the mysql database");
			
			// DB Connection Info
			con = null;
			String url = "jdbc:mysql://172.30.79.95:3306/Weather";
			String username = "weather";
			String password = "weathPiProject361";
			
//			String url = "jdbc:mysql://localhost:3306/weather";
//			String username = "root";
//			String password = "root";

			try {
				// Connect to database
				con = DriverManager.getConnection(url, username, password);
				logging.info("Connection is made");
			} catch (SQLException e) {
				logging.severe("Connection failed with SQLException");
				e.printStackTrace();
			}
		}
		logging.info("Exiting WeatherDAO.makeConnection()");
	}

	@Override
	public boolean create(WeatherSensorModel model) {
		logging.info("Model info: Humidity " + model.getHumidity() + 
				" Pressure " + model.getHumidity() + 
				" Time " + model.getTime());
		
		makeConnection();

		try {
			model = WeatherSensorFactory.getWeather(model.getHumidity(), model.getPressure());
			logging.info("Time: " + Timestamp.valueOf(model.getTime()));
			// Query for # of rows with matching username and password
			String sql1 = "INSERT INTO readings (HUMIDITY, PRESSURE, CURRDATE) VALUES (?, ?, ?)";
			PreparedStatement stmt1 = con.prepareStatement(sql1);
			stmt1.setDouble(1, model.getHumidity());
			stmt1.setDouble(2, model.getPressure());
			stmt1.setTimestamp(3, Timestamp.valueOf(model.getTime()));
			int rowsAffected = stmt1.executeUpdate();

			// Throw DatabaseErrorException is rows affected is less than 1
			if (rowsAffected < 1) {
				logging.severe("Database error: <1 rows were updated, model not added to database.");
				throw new SQLException();
			}
			
			logging.info("The model was successfully inserted.");
			stmt1.close();
		} catch (SQLException e) {
			e.printStackTrace();
			logging.severe("A SQLException error was thrown, model not added to database");
			throw new DatabaseErrorException(e);
		} finally {
			logging.info("Closing database connection.");
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
		logging.info("Entering WeatherDAO.findAll()");
		
		makeConnection();
		List<WeatherSensorModel> weatherList = new ArrayList<WeatherSensorModel>();

		try {
			String sql1 = "SELECT * FROM readings ORDER BY ID DESC LIMIT 10";
			Statement stmt1 = con.createStatement();
			ResultSet rs1 = stmt1.executeQuery(sql1);

			while (rs1.next()) {
				String time = rs1.getTimestamp("CURRDATE").toString();
				System.out.println("Retrieved Time: " + time);
				WeatherSensorModel weather = WeatherSensorFactory.getWeather(rs1.getInt("HUMIDITY"), rs1.getInt("PRESSURE"),
						time);
				
				logging.info("Adding model: Humidity " + weather.getHumidity() + 
						" Pressure " + weather.getHumidity() + 
						" Time " + weather.getTime());

				weatherList.add(weather);
			}
			rs1.close();
			stmt1.close();
		} catch (SQLException e) {
			e.printStackTrace();
			logging.severe("A SQLException error was thrown, model not added to database");
			throw new DatabaseErrorException(e);
		} finally {
			logging.info("Closing database connection.");
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
