package com.example.springbootweb.rest.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootweb.model.Purchase;
import com.example.springbootweb.repo.PurchaseRepository;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {
	PurchaseRepository repo;
	
	public PurchaseController(PurchaseRepository repo) {
		this.repo = repo;
	}
	
	@PostMapping
	public void save(@RequestBody Purchase p) {
		repo.persist(p);
	}
	
	@GetMapping
	public List<Purchase>getAll() {
		return repo.findAllPurchases();
	}
}
