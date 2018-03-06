package com.mark.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mark.exception.AlreadyRegisteredException;
import com.mark.exception.BadLoginException;
import com.mark.exception.DatabaseErrorException;
import com.mark.beans.Registration;
import com.mark.beans.User;

public class UserDAO {
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
	
	public void findByUser(User user) {
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
			rs.next();
			int count = rs.getInt("COUNT");
			
			// Throw BadLoginException if count is less than 1
			if (count < 1) {
				throw new BadLoginException();
			}
		} catch(SQLException e) {

			e.printStackTrace();
			throw new DatabaseErrorException(e); // TODO maybe better error to give...
		} catch (BadLoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createUser(Registration user) {
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
		} catch(SQLException e) {
			throw new DatabaseErrorException(e); // TODO maybe better error to give...
		}
	}
}
