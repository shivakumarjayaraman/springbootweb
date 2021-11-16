package com.example.springbootweb.clients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import com.example.springbootweb.model.Product;

@Component
public class ProductRestTemplateClient {
	private final RestTemplate client = new RestTemplate();
	
	@Value("${serviceurl}")
	private String url;
	
	
	public Product getProduct() {
		String uri = url + "/vodka";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Name", "Value");
		
		ResponseEntity<Product> resp = client.exchange(uri, HttpMethod.GET, null, Product.class);
		return resp.getBody();
	}
	
	public Product incrprice(Product p) {
		String uri = url + "/incrprice";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Name", "Value");
		
		HttpEntity<Product> entity = new HttpEntity<Product>(p, headers);
		ResponseEntity<Product> resp = client.exchange(uri, HttpMethod.POST, entity, Product.class);
		return resp.getBody();
	}
}
