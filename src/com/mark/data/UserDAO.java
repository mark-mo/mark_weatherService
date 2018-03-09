package com.mark.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.mark.exception.AlreadyRegisteredException;
import com.mark.exception.BadLoginException;
import com.mark.exception.DatabaseErrorException;

import com.mark.beans.Registration;

public class UserDAO implements DataAccessInterface<Registration> {
	private Connection con;
	
	public UserDAO() {
		// null at first, to be set later
		con = null;
	}
	
	public void makeConnection() {
		System.out.println("In makeConnection()");
		if (con == null) {
			// DB Connection Info
			con = null;
			System.out.println("Connection is null");
			String url = "jdbc:mysql://172.30.79.95:3306/Weather";
			String username = "weather";
			String password = "weathPiProject361";

			try {
				// Connect to database
				con = DriverManager.getConnection(url, username, password);

				System.out.println("Connection is made");
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
	@Override
	public Registration findBy(Registration user) {
		makeConnection();

		System.out.println("In findByUser");
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
		} catch(SQLException e) {

			e.printStackTrace();
			throw new DatabaseErrorException(e); // TODO maybe better error to give...
		} catch (BadLoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		}
		catch(SQLException e) {
			throw new DatabaseErrorException(e); // TODO maybe better error to give...
		}
		finally {
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
}
