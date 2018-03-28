package com.mark.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ManagedBean
@ViewScoped
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
	
	public Registration(User user) {
		super(user.getUsername(), user.getPassword());
		passwordConfirm = user.getPassword();
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
}
