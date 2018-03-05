package com.mark.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.mark.beans.WeatherSensorModel;
import com.mark.exception.DatabaseErrorException;

@Stateless
@Local(DataAccessInterface.class)
@LocalBean
public class WeatherDAO implements DataAccessInterface<WeatherSensorModel> {
	private Connection con;

	public WeatherDAO() {
		// null at first, to be set later
		con = null;
	}

	/**
	 * Sets connection to class variable con
	 */
	public void makeConnection() {
		if (con == null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://joshsand.com:3306/weather-pi", "just-for-weather", "weathPiProject361");
			} catch(SQLException | ClassNotFoundException e) {
				throw new DatabaseErrorException(e);
			}
		}
	}

	/**
	 * Adds a WeatherSensorModel reading to the database.
	 * 
	 * @param model
	 *            Reading to add (with properties "humidity", "pressure", and "time").
	 * @return boolean
	 *            Always returns true. Can probably convert method to void
	 */
	@Override
	public boolean create(WeatherSensorModel model) {
		makeConnection();

		try {
			// Build inesrt query
			String query = "INSERT INTO READINGS (HUMIDITY, PRESSURE, DATETIME) VALUES (?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setDouble(1, model.getHumidity());
			stmt.setDouble(2, (long) model.getPressure());
			stmt.setString(3, model.getTime());

			// Execute query
			stmt.executeUpdate();
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

	/**
	 * Returns list of every WeatherSensorModel from the database.
	 * 
	 * @return List<WeatherSensorModel>
	 *            All readings (with properties "humidity", "pressure", and "time").
	 */
	@Override
	public List<WeatherSensorModel> findAll() {
		makeConnection();
		List<WeatherSensorModel> weatherList = new ArrayList<WeatherSensorModel>();

		try {
			String sql1 = "SELECT * FROM READINGS";
			Statement stmt1 = con.createStatement();
			ResultSet rs1 = stmt1.executeQuery(sql1);
			while (rs1.next()) {
				WeatherSensorModel weather = new WeatherSensorModel(rs1.getInt("HUMIDITY"), rs1.getInt("PRESSURE"), rs1.getString("DATETIME"));
				weatherList.add(weather);
			}
			rs1.close();
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
		return weatherList;
	}

	@Override
	public WeatherSensorModel findByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WeatherSensorModel findBy(WeatherSensorModel model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(WeatherSensorModel model) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(WeatherSensorModel model) {
		// TODO Auto-generated method stub
		return false;
	}
}
