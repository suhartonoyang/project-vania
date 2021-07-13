package com.project.bayes.vania.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bayes")
@CrossOrigin
public class HelloWorldController {

	@GetMapping("/helloWorld")
	public String helloWorld() {
		return "Project Vania Hello World";
	}

}
