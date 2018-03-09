package com.mark.business;

import com.mark.beans.User;
import com.mark.data.DataAccessInterface;
import com.mark.exception.BadLoginException;
import com.mark.exception.PasswordMismatchException;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.mark.beans.Registration;

@Stateless
@Local(UserServiceInterface.class)
@LocalBean
public class UserService implements UserServiceInterface {
	@EJB
	DataAccessInterface<User> service;
	
	public UserService() {
	}
	
	public void login(User user) throws BadLoginException {
		service.findBy(user);
	}
	
	public void register(Registration registration) {
		// Throw PasswordMismatchException if passwords do not match
		String password = registration.getPassword();
		String confirm = registration.getPasswordConfirm();
		if (!password.equals(confirm)) {
			throw new PasswordMismatchException();
		}
		
		// Otherwise send to DAO for insert
		service.create(registration);
	}
}
