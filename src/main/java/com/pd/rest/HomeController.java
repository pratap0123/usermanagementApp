package com.pd.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

	@GetMapping("/home")
	public String getHome() {
		return "Welcome To Apna Bank";

	}

	@GetMapping("/contact")
	public String getContact() {
		return "Contact Our Banking Admin";

	}

	@GetMapping("/balance")
	public String getBalance() {
		return "Balance:Rs 300000";

	}

}
