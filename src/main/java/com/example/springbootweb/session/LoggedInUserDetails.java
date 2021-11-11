package com.example.springbootweb.session;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
public class LoggedInUserDetails {
	private String user;

	public LoggedInUserDetails() {
		System.out.println("Inside dETAILS obj cons");
	}
	public String getLoggedInUser() {
		return user;
	}

	public void setLoggedInUser(String username) {
		this.user = username;
	}
	
	
}
