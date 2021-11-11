package com.example.springbootweb.request;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.example.springbootweb.session.LoggedInUserDetails;

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

	
	public boolean login(LoggedInUserDetails details) {
		System.out.println(this.username + ":" + this.password + ":" + this.hashCode());
		boolean loggedIn = "shiva".equalsIgnoreCase(username) && "password".equalsIgnoreCase(password);
		System.out.println("Login Success : " + loggedIn);
		System.out.println("Details " + details);
		
		if (loggedIn && (details != null)) {
			details.setLoggedInUser(this.username);
		}
		return loggedIn;
	}
}
