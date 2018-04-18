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

import com.mark.beans.SensorModel;
import com.mark.exception.DatabaseErrorException;

import com.mark.util.LoggingInterceptor;
import com.mark.util.LoggingInterface;
import com.mark.util.WeatherSensorFactory;

@Stateless
@Interceptors(LoggingInterceptor.class)
@Local(DataAccessInterface.class)
@LocalBean
public class WeatherDAO implements DataAccessInterface<SensorModel> {
	private Connection con;

	@Inject
	LoggingInterface logging;

	public WeatherDAO() {
		// null at first, to be set later
		con = null;
	}

	public void makeConnection() {
		logging.info("Entering WeatherDAO.makeConnection()");

		logging.info("Creating a connection to the mysql database");

		// DB Connection Info
		if (con == null) {
			// String url = "jdbc:mysql://172.30.79.95:3306/Weather";
			// String username = "weather";
			// String password = "weathPiProject361";

			String url = "jdbc:mysql://localhost:3306/weather";
			String username = "root";
			String password = "root";

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
	public boolean create(SensorModel model) {
		logging.info("Model info: Humidity " + model.getFirstAtt() + " Pressure " + model.getSecondAtt() + " Time "
				+ model.getTime());

		makeConnection();

		try {
			model = (SensorModel) WeatherSensorFactory.getWeather(model.getFirstAtt(), model.getSecondAtt());
			logging.info("Time: " + Timestamp.valueOf(model.getTime()));
			// Query for # of rows with matching username and password
			String sql1 = "INSERT INTO readings (HUMIDITY, PRESSURE, CURRDATE) VALUES (?, ?, ?)";
			PreparedStatement stmt1 = con.prepareStatement(sql1);
			stmt1.setDouble(1, model.getFirstAtt());
			stmt1.setDouble(2, model.getSecondAtt());
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
					con = null;
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
	public List<SensorModel> findAll() {
		logging.info("Entering WeatherDAO.findAll()");

		makeConnection();
		List<SensorModel> weatherList = new ArrayList<SensorModel>();

		try {
			String sql1 = "SELECT * FROM readings ORDER BY ID DESC LIMIT 10";
			Statement stmt1 = con.createStatement();
			ResultSet rs1 = stmt1.executeQuery(sql1);

			while (rs1.next()) {
				String time = rs1.getTimestamp("CURRDATE").toString();
				System.out.println("Retrieved Time: " + time);
				SensorModel weather = (SensorModel) WeatherSensorFactory.getWeather(rs1.getInt("HUMIDITY"),
						rs1.getInt("PRESSURE"), time);

				logging.info("Adding model: Humidity " + weather.getFirstAtt() + " Pressure " + weather.getSecondAtt()
						+ " Time " + weather.getTime());

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
					con = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new DatabaseErrorException(e);
				}
			}
		}
		return weatherList;
	}

	@Override
	public SensorModel findByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SensorModel findBy(SensorModel t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(SensorModel t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(SensorModel t) {
		// TODO Auto-generated method stub
		return false;
	}
}
