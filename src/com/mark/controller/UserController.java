package com.mark.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.mark.business.UserService;
import com.mark.exception.AlreadyRegisteredException;
import com.mark.exception.BadLoginException;
import com.mark.exception.DatabaseErrorException;
import com.mark.exception.PasswordMismatchException;
import com.mark.beans.Registration;
import com.mark.beans.User;

@ManagedBean
@ViewScoped
public class UserController {

	public String loginSubmit() {
		// Get the User model from POST
		FacesContext context = FacesContext.getCurrentInstance();
		User user = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
		
		// Send User to service for verification
		UserService service = new UserService();
		try {
			service.login(user);
		} catch(BadLoginException e) {
			// Send bad-login message to View
			String message = "Login failed. Please try again.";
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("message", message);
			return "message.xhtml";
		} catch(DatabaseErrorException e) {
			// Send generic database error message to View
			String message = "An error has occurred. Please try again later.";
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("message", message);
			return "message.xhtml";
		}
				
		// Send login-successful message to View
		String message = "You have successfully logged on, " + user.getUsername();
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("message", message);
		return "message.xhtml";
	}
	
	public String registerSubmit() {
		// Get the Registration model from POST
		FacesContext context = FacesContext.getCurrentInstance();
		Registration registration = context.getApplication().evaluateExpressionGet(context, "#{registration}", Registration.class);
		
		// Send Registration to service for verification + insert
		UserService service = new UserService();
		try {
			service.register(registration);
		} catch(PasswordMismatchException e) {
			// Send generic database error message to View
			String message = "The passwords did not match. Please type the same password in both boxes.";
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("message", message);
			return "message.xhtml";
		} catch(AlreadyRegisteredException e) {
			// Send generic database error message to View
			String message = "A user by that named is already registered. Please log in or choose a new username.";
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("message", message);
			return "message.xhtml";
		} catch(DatabaseErrorException e) {
			// Send generic database error message to View
			String message = "An error has occurred. Please try again later.";
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("message", message);
			return "message.xhtml";
		}
		
		// Send login-successful message to View
		String message = "You have successfully registered, " + registration.getUsername();
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("message", message);
		return "message.xhtml";
	}
}
