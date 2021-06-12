package com.project.bayes.vania.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.bayes.vania.bean.Request;
import com.project.bayes.vania.bean.Result;
import com.project.bayes.vania.service.BayesDiagnosaAnjingService;
import com.project.bayes.vania.service.BayesDiagnosaKucingService;
import com.project.bayes.vania.service.BayesNaiveService;

@RestController
@RequestMapping("/api/bayes")
@CrossOrigin
public class HelloWorldController {

	@GetMapping("/helloWorld")
	public String helloWorld() {
		return "Project Vania Hello World";
	}

}
