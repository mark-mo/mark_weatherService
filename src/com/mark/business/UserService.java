package com.mark.business;

import com.mark.model.User;
import com.mark.data.UserDAO;
import com.mark.exception.BadLoginException;
import com.mark.exception.PasswordMismatchException;
import com.mark.model.Registration;

public class UserService {
	UserDAO service;
	
	public UserService() {
		service = new UserDAO();
	}
	
	public void login(User user) throws BadLoginException {
		service.findByUser(user);
	}
	
	public void register(Registration registration) {
		// Throw PasswordMismatchException if passwords do not match
		String password = registration.getPassword();
		String confirm = registration.getPasswordConfirm();
		if (!password.equals(confirm)) {
			throw new PasswordMismatchException();
		}
		
		// Otherwise send to DAO for insert
		service.createUser(registration);
	}
}
