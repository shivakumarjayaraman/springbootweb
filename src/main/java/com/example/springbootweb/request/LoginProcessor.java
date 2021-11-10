package com.example.springbootweb.request;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class LoginProcessor {
	private String username;
	private String password;
	
	
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


	public LoginProcessor() {
		System.out.println("Inside LP constructor");
	}
	
	public boolean login() {
		System.out.println(this.username + ":" + this.password);
		boolean loggedIn = "shiva".equalsIgnoreCase(username) && "password".equalsIgnoreCase(password);
		System.out.println("Login Success : " + loggedIn);
		return loggedIn;
	}
}
