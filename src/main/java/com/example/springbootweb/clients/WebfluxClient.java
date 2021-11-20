package com.example.springbootweb.clients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.springbootweb.model.Product;

import reactor.core.publisher.Mono;

@Component
public class WebfluxClient {
	@Value("${serviceurl}")
	private String url;
	
	private WebClient client;
	
	public WebfluxClient(WebClient webClient) {
		this.client = webClient;
	}
	
	public WebClient getClient() {
		return client;
	}
	
	public Mono<Product> getProduct() { 
		return client.get().uri(url + "/vodka").header("Name",  "Value").
			retrieve().bodyToMono(Product.class);
	}
	
	public Mono<Product> incrprice(Product p) {
		return client.post().uri(url + "/incrprice").header("Name",  "Value").
				body(Mono.just(p), Product.class).
				retrieve().bodyToMono(Product.class);
	}
}
