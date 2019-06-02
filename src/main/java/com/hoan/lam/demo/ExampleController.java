package com.hoan.lam.demo;

import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RestController
public class ExampleController {

	@RequestMapping("/")
	public String index() {
		log.info("get method is calling... {}");
		return "Greetings from Spring Boot!";
	}

	@PostMapping("/load")
	public String load(@RequestBody Object obj) {
		if (obj != null) {
			log.info("post method is loading... {}", obj.toString());
			return obj.toString();
		}
		log.info("post method is loading...");
		return "Greetings from Spring Boot!";
	}

}