package com.example.springbootweb;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import com.example.springbootweb.clients.WebfluxClient;
import com.example.springbootweb.controllers.MainController;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private ApplicationContext ctx;
	
	@Test
	void contextLoads() {
		assertTrue(ctx != null);
		MainController bean = ctx.getBean(MainController.class);
		assertTrue(bean != null);
		
		String s = "helo";

		
		WebfluxClient wfc = ctx.getBean(WebfluxClient.class);
		assertTrue(wfc != null && wfc.getClient() != null);
		
	}

}
