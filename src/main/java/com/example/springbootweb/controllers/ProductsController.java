package com.example.springbootweb.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.springbootweb.model.Product;
import com.example.springbootweb.service.ProductService;

@Controller
public class ProductsController {
	private final ProductService productService;
	
	public ProductsController(ProductService ps) {
		this.productService = ps;
	}
	
	@GetMapping("/products")
	public String viewProducts(Model model) {
		List<Product> products = productService.findAll();
		model.addAttribute("products", products);
		return "products.html";
	}
	
	@PostMapping("/products")
	public String addProduct(Product p, Model m) {
		productService.addProduct(p);
		
		return viewProducts(m);
	}
}
