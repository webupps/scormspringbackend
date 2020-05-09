package com.webupps.custom.app.resource;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/greeting")
public class GreetingController {
	
	@GetMapping("/hello")
	public String hello(Principal principal) {
		
		return "Hello, " + principal.getName();
	}

}
