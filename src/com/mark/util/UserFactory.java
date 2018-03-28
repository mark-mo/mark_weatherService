package com.mark.util;

import java.util.HashMap;

import com.mark.beans.User;

public class UserFactory {
	private static final HashMap<String, User> userMap = new HashMap<String, User>();

	public static User getUser(String username, String password) {
		User user = (User)userMap.get(username);
		
		if(user == null) {
			user = new User(username, password);
			userMap.put(username,  user);
			System.out.println("Creating user : " + username);
		}
		return user;
	}
}
