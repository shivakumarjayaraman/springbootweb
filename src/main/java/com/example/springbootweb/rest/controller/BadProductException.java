package com.example.springbootweb.rest.controller;

public class BadProductException extends RuntimeException {
	public BadProductException(String s) {
		super(s);
	}
}
