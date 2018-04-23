package com.mark.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import com.mark.exception.AlreadyRegisteredException;
import com.mark.exception.BadLoginException;
import com.mark.exception.DatabaseErrorException;
import com.mark.util.LoggingInterface;
import com.mark.beans.Registration;

@Stateless
@Local(DataAccessInterface.class)
@LocalBean
public class UserDAO implements DataAccessInterface<Registration> {
	@Inject
	LoggingInterface logging;

	private Connection con;

	public UserDAO() {
		// null at first, to be set later
		con = null;
	}

	public void makeConnection() {
		logging.info("Entering UserDAO.makeConnection()");
		System.out.println("In makeConnection()");
		logging.info("Creating a connection to the mysql database");
		// DB Connection Info
		if (con == null) {
			System.out.println("Connection is null");
			String url = "jdbc:mysql://172.30.196.169:3306/Weather";
			String username = "weather";
			String password = "weathPiProject361";

			try {
				// Connect to database
				con = DriverManager.getConnection(url, username, password);

				logging.info("Connection is made");
			} catch (SQLException e) {
				logging.severe("Connection failed with SQLException");
				e.printStackTrace();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		logging.info("Exiting UserDAO.makeConnection()");
	}

	@Override
	public Registration findBy(Registration user) {
		logging.info("Entering UserDAO.findBy()");
		makeConnection();
		try {
			// Query for # of rows with matching username and password
			String query = "SELECT COUNT(*) AS COUNT FROM USERS WHERE USERNAME=? AND PASSWORD=?";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());

			// Execute query and get COUNT from results
			ResultSet rs = stmt.executeQuery();
			if (!rs.next()) {
				rs.close();
				stmt.close();

				throw new BadLoginException();
			}
			user.setUsername(rs.getString(1));
			user.setPassword(rs.getString(2));
			rs.close();
			stmt.close();
			logging.info("User " + user.getUsername() + " found");
		} catch (SQLException e) {
			logging.warning("Database failed with SQLException");
			e.printStackTrace();
			throw new DatabaseErrorException(e); // TODO maybe better error to give...
		} catch (BadLoginException e) {
			logging.warning("Failed Log in with a BadLoginException");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// Cleanup Database
			if (con != null) {
				try {
					con.close();
					con = null;
					logging.info("Database connection closed");
				} catch (SQLException e) {
					logging.warning("Database failed with SQLException");
					e.printStackTrace();
					throw new DatabaseErrorException(e);
				}
			}
		}

		logging.info("Exiting UserDAO.findBy()");
		return user;
	}

	public boolean create(Registration user) {
		makeConnection();

		try {
			// Query for any rows with same username
			String query = "SELECT COUNT(*) AS COUNT FROM USERS WHERE USERNAME=?";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, user.getUsername());

			// Execute query and get COUNT from results
			ResultSet rs = stmt.executeQuery();
			rs.next();
			int count = rs.getInt("COUNT");

			// Throw AlreadyRegisteredException if count is more than 0
			if (count > 0) {
				throw new AlreadyRegisteredException();
			}
			rs.close();

			// Otherwise, add to USERS
			query = "INSERT INTO USERS (USERNAME, PASSWORD) VALUES (?,?);";
			stmt = con.prepareStatement(query);
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			int rowsAffected = stmt.executeUpdate();

			// Throw DatabaseErrorException is rows affected is less than 1
			if (rowsAffected < 1) {
				throw new SQLException();
			}
			stmt.close();
		} catch (SQLException e) {
			throw new DatabaseErrorException(e); // TODO maybe better error to give...
		} finally {
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

	@Override
	public List<Registration> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Registration findByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Registration t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Registration t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Registration> findSelect(int amount) {
		// TODO Auto-generated method stub
		return null;
	}
}
