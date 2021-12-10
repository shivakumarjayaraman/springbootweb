package com.example.springbootweb.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootweb.model.Account;
import com.example.springbootweb.service.AccountService;

@RestController
public class AccountController {
	
	@Autowired
	private AccountService service;
	
	@GetMapping("/accounts")
	public List<Account> getAccount(
			@RequestParam(name = "name") String name) {
		return service.getAll(name);
	}
	
	@PostMapping("/accounts")
	public void transfer(
			@RequestParam(name = "src") String src,
			@RequestParam(name = "dest") String dest,
			@RequestParam(name = "amount") float amount,
			@RequestParam(name = "fail", required = false) String fail
			) {
		
		boolean rollback = (fail != null && fail.length() > 0);
		service.transfer(src, dest, amount, rollback);
	}
}
