package com.example.springbootweb.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootweb.clients.ProductFeignClient;
import com.example.springbootweb.clients.ProductRestTemplateClient;
import com.example.springbootweb.clients.WebfluxClient;
import com.example.springbootweb.model.Product;

import reactor.core.publisher.Mono;

@RestController
public class RestClientController {
	ProductFeignClient client;
	ProductRestTemplateClient rtClient;
	WebfluxClient wfclient;
	
	public RestClientController(ProductFeignClient c, ProductRestTemplateClient rtc, WebfluxClient wfclient) {
		this.client = c;
		this.rtClient = rtc;
		this.wfclient = wfclient;
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
	
	@GetMapping("/ip2")
	public Mono<Product> incrProductPrice2() {
		Product p = getProduct(null);
		
		return wfclient.incrprice(p);
	}
}
