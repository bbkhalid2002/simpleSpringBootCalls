package com.example.server;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonInclude;

@RestController
public class HomeController {

	@GetMapping
	public String index() {
		return "Server working ...";
	}
	
	@PostMapping
	public ResponseEntity<Object> sayHello(@RequestBody medto name) {
		System.out.println(" ****** REACHED ******");
		return new ResponseEntity<Object>(name,HttpStatus.OK);
	}
}

@JsonInclude
class medto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}