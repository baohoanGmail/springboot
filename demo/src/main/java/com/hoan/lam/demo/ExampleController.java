package com.hoan.lam.demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class ExampleController {

	@RequestMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@PostMapping("/load")
	public String load(@RequestBody Object obj) {
		if (obj != null) {
			return obj.toString();
		}
		return "Greetings from Spring Boot!";
	}

}