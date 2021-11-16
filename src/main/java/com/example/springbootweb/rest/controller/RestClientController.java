package com.example.springbootweb.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootweb.clients.ProductFeignClient;
import com.example.springbootweb.clients.ProductRestTemplateClient;
import com.example.springbootweb.model.Product;

@RestController
public class RestClientController {
	ProductFeignClient client;
	ProductRestTemplateClient rtClient;
	
	public RestClientController(ProductFeignClient c, ProductRestTemplateClient rtc) {
		this.client = c;
		this.rtClient = rtc;
	}
	
	@GetMapping("/client")
	public Product getProduct(
			@RequestParam(name = "usert", required = false) String usert
			) {
		if (usert != null) {
			return rtClient.getProduct();
		}
		return client.getProduct();
	}
	
	@GetMapping("/ip")
	public Product incrProductPrice(
			@RequestParam(name = "usert", required = false) String usert
			) {
		//return client.incrprice(getProduct(usert));
		return rtClient.incrprice(getProduct("true"));
	}
}
