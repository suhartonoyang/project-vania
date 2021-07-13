package com.project.bayes.vania.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.bayes.vania.bean.Request;
import com.project.bayes.vania.bean.Response;
import com.project.bayes.vania.service.BayesDiagnosaAnjingService;
import com.project.bayes.vania.service.BayesDiagnosaKucingService;

@RestController
@RequestMapping("/api/bayes")
@CrossOrigin
public class TestingController {

	@Autowired
	private BayesDiagnosaAnjingService bayesDiagnosaAnjingService;

	@Autowired
	private BayesDiagnosaKucingService bayesDiagnosaKucingService;

	@PostMapping("/testing/anjing")
	public ResponseEntity<Response> anjingTesting(@RequestBody List<Request> requests) {
		Response resp = new Response();
		resp.setCode(String.valueOf(HttpStatus.CREATED.value()));
		resp.setMessage(HttpStatus.CREATED.name());
		Map<String, Object> dataMap = bayesDiagnosaAnjingService.run(requests);
		resp.setData(Arrays.asList(dataMap));
		return ResponseEntity.ok().body(resp);
	}

	@PostMapping("/testing/kucing")
	public ResponseEntity<Response> kucingTesting(@RequestBody List<Request> requests) {
		Response resp = new Response();
		resp.setCode(String.valueOf(HttpStatus.CREATED.value()));
		resp.setMessage(HttpStatus.CREATED.name());
		Map<String, Object> dataMap = bayesDiagnosaKucingService.run(requests);
		resp.setData(Arrays.asList(dataMap));
		return ResponseEntity.ok().body(resp);
	}
}
