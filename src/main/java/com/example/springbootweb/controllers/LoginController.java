package com.example.springbootweb.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.springbootweb.request.LoginProcessor;
import com.example.springbootweb.session.LoggedInUserDetails;

@Controller
public class LoginController {

	@GetMapping("/") 
	public String loginGet(HttpSession session, Model model) {
		LoggedInUserDetails details = (LoggedInUserDetails) session.getAttribute("details");
		if (details != null && details.getLoggedInUser() != null) {
			model.addAttribute("username", details.getLoggedInUser());
			return "hello.html";
		}
		return "login.html";
	}
	
	@PostMapping("/") 
	public String loginPost(
			HttpSession session,
			LoginProcessor loginProcessor,
			//LoggedInUserDetails details, // This should be set to a session scoped object, but its not .. 
			Model model) {
		LoggedInUserDetails details = (LoggedInUserDetails) session.getAttribute("details");
		if (details == null) {
			details = new LoggedInUserDetails(); // this is wrong , but SessionScope is for some reason not working
		}
		loginProcessor.login(details);
		
		if (details.getLoggedInUser() == null) {
			return "redirect:/";
		}
		
		// workaround since SessionScope is not working for some reason.. 
		session.setAttribute("details", details);
		System.out.println("User name :  " + details.getLoggedInUser());
		model.addAttribute("username", details.getLoggedInUser());
		return "hello.html";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("details");
		return "redirect:/";
	}
}
