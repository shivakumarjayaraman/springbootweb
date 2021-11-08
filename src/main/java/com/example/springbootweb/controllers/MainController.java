package com.example.springbootweb.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

	static {
		System.out.println("Class Loaded");
	}

	public MainController() {
		System.out.println("Inside constructor");
	}

	@RequestMapping("/home")
	public String home(Model page, 
			@RequestParam(required = false) String color) {
		page.addAttribute("username", "Shiva");
		page.addAttribute("color", color);
		return "hello.html";
	}

	@RequestMapping("/greet/{name}")
	public String greet(Model page, 
			@PathVariable String name
			) {
		page.addAttribute("username", name);
		page.addAttribute("color", "blue");
		return "hello.html";
	}
}
