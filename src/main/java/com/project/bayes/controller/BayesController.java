package com.project.bayes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.bayes.bean.Request;
import com.project.bayes.bean.Result;
import com.project.bayes.service.BayesDiagnosaAnjingService;
import com.project.bayes.service.BayesDiagnosaKucingService;
import com.project.bayes.service.BayesNaiveService;

@RestController
@RequestMapping("/api/bayes")
@CrossOrigin
public class BayesController {

	@Autowired
	private BayesDiagnosaAnjingService bayesDiagnosaAnjingService;

	@Autowired
	private BayesDiagnosaKucingService bayesDiagnosaKucingService;

	@GetMapping("/helloWorld")
	public String helloWorld() {
		return "Hello World";
	}

	@PostMapping("/anjing/diagnose")
	public ResponseEntity<?> dogDiganose(@RequestBody List<Request> requests) {
		Result result = bayesDiagnosaAnjingService.run(requests);

		return ResponseEntity.ok().body(result);
	}

	@PostMapping("/run")
	public ResponseEntity<?> catDiagnose(@RequestBody List<Request> requests) {
		Result result = bayesDiagnosaKucingService.run(requests);

		return ResponseEntity.ok().body(result);
	}
}
