package com.hoan.lam.demo;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RestHelper<T> {

	public static String endpoint = "http://5c7eb16b08d03100141af483.mockapi.io";

	private static HttpHeaders headers = null;
	static {
		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		headers.setAccessControlAllowOrigin("*");
	}

	public static <T> ResponseEntity<?> post(String url, T data, Class<T> clazz) {
		log.info("Post event is starting....");
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<T> entity = new HttpEntity<T>(data, headers);
		ResponseEntity<T> response = null;
		try {
			log.info("[REST API] Calling to endpoint {}", url);
			response = restTemplate.exchange(url, HttpMethod.POST, entity, clazz);
			log.debug("[REST API] Response result: {}", JsonHelper.object2String(response, "RestHelper"));
		} catch (Exception ex) {
			log.error("[REST API] Post failed: " + ex.getMessage());
		}
		return response;
	}

	public static <T> ResponseEntity<?> get(String url) {
		RestTemplate restTemplate = new RestTemplate();
		URI uri;
		ResponseEntity<String> result = null;
		try {
			uri = new URI(url);
			result = restTemplate.getForEntity(uri, String.class);
			log.debug("[REST API] ** get response: {}", JsonHelper.object2String(result, "RestHelper"));
		} catch (URISyntaxException e) {
			log.error("[REST API] Failed {}", e.getMessage());
		}
		return result;
	}

	public static final String getEndpoint(String type) {
		if ("users".equalsIgnoreCase(type)) {
			return endpoint.concat("/users");
		}
		if ("products".equalsIgnoreCase(type)) {
			return endpoint.concat("/products");
		}
		return endpoint;
	}
}
