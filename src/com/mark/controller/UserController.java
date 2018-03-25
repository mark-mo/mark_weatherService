package com.mark.controller;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.mark.business.UserServiceInterface;
import com.mark.exception.AlreadyRegisteredException;
import com.mark.exception.BadLoginException;
import com.mark.exception.DatabaseErrorException;
import com.mark.exception.PasswordMismatchException;
import com.mark.util.LoggingInterface;
import com.mark.beans.Registration;
import com.mark.beans.User;

@ManagedBean
@ViewScoped
public class UserController {
	@Inject
	LoggingInterface logging;

	@EJB
	UserServiceInterface service;
	
	public String loginSubmit() {
		logging.info("Enter into UserController:loginSubmit");
		// Get the User model from POST
		FacesContext context = FacesContext.getCurrentInstance();
		User user = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
		
		// Send User to service for verification
		try {
			service.login(user);
			logging.info("Successful login");
		} catch(BadLoginException e) {
			// Send bad-login message to View
			String message = "Login failed. Please try again.";
			logging.warning(message);
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("message", message);
			return "message.xhtml";
		} catch(DatabaseErrorException e) {
			// Send generic database error message to View
			String message = "An error has occurred. Please try again later.";
			logging.severe(message);
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("message", message);
			return "message.xhtml";
		}
				
		// Send login-successful message to View
		String message = "You have successfully logged on, " + user.getUsername();
		logging.info(message);
		logging.info("Exiting UserController:loginSubmit");
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("message", message);
		return "message.xhtml";
	}
	
	public String registerSubmit() {
		logging.info("Enter into UserController:registerSubmit");
		// Get the Registration model from POST
		FacesContext context = FacesContext.getCurrentInstance();
		Registration registration = context.getApplication().evaluateExpressionGet(context, "#{registration}", Registration.class);
		
		// Send Registration to service for verification + insert
		try {
			service.register(registration);
			logging.info("Successfully registered");
		} catch(PasswordMismatchException e) {
			// Send generic database error message to View
			String message = "The passwords did not match. Please type the same password in both boxes.";
			logging.warning(message);
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("message", message);
			return "message.xhtml";
		} catch(AlreadyRegisteredException e) {
			// Send generic database error message to View
			String message = "A user by that named is already registered. Please log in or choose a new username.";
			logging.warning(message);
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("message", message);
			return "message.xhtml";
		} catch(DatabaseErrorException e) {
			// Send generic database error message to View
			String message = "An error has occurred. Please try again later.";
			logging.severe(message);
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("message", message);
			return "message.xhtml";
		}
		
		// Send login-successful message to View
		String message = "You have successfully registered, " + registration.getUsername();
		logging.info(message);
		logging.info("Exit UserController:registerSubmit");
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("message", message);
		return "message.xhtml";
	}
}
