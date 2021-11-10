package com.example.springbootweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.springbootweb.request.LoginProcessor;

@Controller
public class LoginController {

	@GetMapping("/") 
	public String loginGet() {
		return "login.html";
	}
	
	@PostMapping("/") 
	public String loginPost(
			LoginProcessor loginProcessor,
			Model model) {
		boolean loggedIn = loginProcessor.login();
		if (loggedIn) {
			model.addAttribute("message", "Logged In");
		}
		return "login.html";
	}
}
