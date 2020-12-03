package com.example.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ClientController {

	@GetMapping
	public String index() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_PLAIN);
		HttpEntity<String> reqeust = new HttpEntity<String>("Khalid", headers);
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<String> responseEntity = restTemplate.postForEntity("https://localhost:8443/server", reqeust, String.class);
		
		
		return "Server working and status ("+String.valueOf(responseEntity.getStatusCode())+")";
	}
}
