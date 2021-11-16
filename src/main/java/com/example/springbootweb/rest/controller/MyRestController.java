package com.example.springbootweb.rest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootweb.model.Product;

@RestController
public class MyRestController {
	@GetMapping("/prod")
	public Product getProduct() {
		Product p = new Product();
		p.setName("icecream");
		p.setPrice(20.45);
		return p;
	}
	
	@GetMapping("/prods")
	public List<Product> getProducts() {
		Product p1 = new Product();
		p1.setName("icecream");
		p1.setPrice(20.45);
		
		Product p2 = new Product();
		p2.setName("beer");
		p2.setPrice(10.99);
		
		return List.of(p1, p2);
	}
	
	@GetMapping("/vodka")
	public ResponseEntity<Product> getCustomResponse(
			@RequestParam(name = "err", required = false) String err
			) {
		if (err != null) {
			throw new BadProductException("Bad Prod");
		}
		Product p1 = new Product();
		p1.setName("grey goose");
		p1.setPrice(20.45);
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Name", "Some Value").body(p1);
	}
	
	@PostMapping("/incrprice")
	public Product increasePrice(@RequestBody Product p) {
		p.setPrice(p.getPrice() + 2);
		return p;
	}
	
}
