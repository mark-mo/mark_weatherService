package com.mark.business;

import com.mark.beans.Registration;
import com.mark.beans.User;
import com.mark.exception.BadLoginException;

public interface UserServiceInterface {
	public void login(User user) throws BadLoginException;
	
	public void register(Registration registration);
}
