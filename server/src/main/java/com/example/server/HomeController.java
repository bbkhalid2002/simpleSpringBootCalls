package com.example.server;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping
	public String index() {
		return "Server working ...";
	}
	
	@PostMapping
	public ResponseEntity<medto> sayHello(@RequestBody medto name) {
		return new ResponseEntity<medto>(name,HttpStatus.OK);
	}
}

class medto {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}