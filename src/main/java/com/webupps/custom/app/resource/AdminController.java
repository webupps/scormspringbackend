package com.webupps.custom.app.resource;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/admin")
public class AdminController {
	@GetMapping("/hello")
	public String helllo(Principal principal) {
		return "This Admin. Hi, " + principal.getName();
	}
}
