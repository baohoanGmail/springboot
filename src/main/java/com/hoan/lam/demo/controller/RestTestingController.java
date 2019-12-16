package com.hoan.lam.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class RestTestingController {

	@PostMapping(value = "search", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Boolean> search(@RequestBody MultiValueMap<String, Object> parameters) {
		log.info("{}", parameters);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping(value = "search", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Boolean> search(@RequestBody Object parameters) {
		log.info("{}", parameters);
		return ResponseEntity.ok().build();
	}
	
}
