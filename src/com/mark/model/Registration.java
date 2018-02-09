package com.mark.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Registration extends User {
	@NotNull(message="This is a required field.")
	@Size(min=6, message="Password must be 6 or more characters.")
	private String passwordConfirm;

	public Registration() {
		super();
		passwordConfirm = "";
	}
	
	public Registration(String username, String password, String passwordConfirm) {
		super(username, password);
		this.passwordConfirm = passwordConfirm;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
}
