package com.example.springbootweb.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.springbootweb.model.Product;

@FeignClient(name="prod", url="${serviceurl}")
public interface ProductFeignClient {
	@GetMapping("/vodka")
	public Product getProduct();
	
	@PostMapping("/incrprice")
	public Product incrprice(Product p);
}
