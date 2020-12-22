package com.example.server;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonInclude;

@RestController
public class HomeController {

	private Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@GetMapping
	public ResponseEntity<Object> index() {
		String val = System.getenv("SPRING_VARS");

		logger.info("==============" + val);
		Integer customers = jdbcTemplate.queryForObject("select count(*) from customers",Integer.class);
		
		return new ResponseEntity<Object>("value is:" + val + " - "+customers.toString(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Object> sayHello(@RequestBody medto name) {
		System.out.println(" ****** REACHED ******");
		return new ResponseEntity<Object>(name, HttpStatus.OK);
	}
}

@JsonInclude
class medto implements Serializable {

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