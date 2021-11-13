package com.example.springbootweb.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BadProductAdvice {
	@ExceptionHandler(BadProductException.class)
	public ResponseEntity<String> errorOccured() {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Oopsie");
	}
}
