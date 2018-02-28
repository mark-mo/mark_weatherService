package com.mark.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ManagedBean
@ViewScoped
public class User {
	@NotNull(message="This is a required field.")
	@Size(min=3, message="Username must be 3 or more characters.")
	private String username;
	
	@NotNull(message="This is a required field.")
	@Size(min=6, message="Password must be 6 or more characters.")
	private String password;
	
	public User() {
		username = "";
		password = "";
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}