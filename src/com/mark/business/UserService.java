package com.mark.business;

import com.mark.beans.User;
import com.mark.data.DataAccessInterface;
import com.mark.exception.BadLoginException;
import com.mark.exception.PasswordMismatchException;
import com.mark.util.LoggingInterface;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import com.mark.beans.Registration;

@Stateless
@Local(UserServiceInterface.class)
@LocalBean
public class UserService implements UserServiceInterface {
	@Inject
	LoggingInterface logging;
	
	@EJB
	@Named("userDAO")
	DataAccessInterface<User> service;
	
	public UserService() {
	}
	
	public void login(User user) throws BadLoginException {
		logging.info("Enter into UserService:login");
		service.findBy(user);
		logging.info("Exit UserService:login");
	}
	
	public void register(Registration registration) {
		// Throw PasswordMismatchException if passwords do not match
		logging.info("Enter into UserService:register");
		String password = registration.getPassword();
		String confirm = registration.getPasswordConfirm();
		if (!password.equals(confirm)) {
			logging.info("Password does not match");
			throw new PasswordMismatchException();
		}
		
		// Otherwise send to DAO for insert
		service.create(registration);
		logging.info("Exit UserService:register");
	}
}
