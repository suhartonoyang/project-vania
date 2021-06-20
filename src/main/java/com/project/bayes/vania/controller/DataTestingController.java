package com.project.bayes.vania.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.bayes.vania.bean.Response;
import com.project.bayes.vania.model.DataTestingAnjing;
import com.project.bayes.vania.model.DataTestingKucing;
import com.project.bayes.vania.service.DataTestingAnjingService;
import com.project.bayes.vania.service.DataTestingKucingService;

@RestController
@RequestMapping("/api/bayes")
@CrossOrigin
public class DataTestingController {

	@Autowired
	private DataTestingAnjingService dataTestingAnjingService;

	@Autowired
	private DataTestingKucingService dataTestingKucingService;

	@GetMapping("/data-testing-anjing")
	public ResponseEntity<Response> getDataTestingAnjingAll() {
		Response resp = new Response();
		resp.setCode(String.valueOf(HttpStatus.OK.value()));
		resp.setMessage(HttpStatus.OK.name());
		List<DataTestingAnjing> data = dataTestingAnjingService.getDataTestingAnjingAll();
		resp.setData(data);
		return ResponseEntity.ok().body(resp);
	}
	
	@GetMapping("/data-testing-kucing")
	public ResponseEntity<Response> getDataTestingKucingAll() {
		Response resp = new Response();
		resp.setCode(String.valueOf(HttpStatus.OK.value()));
		resp.setMessage(HttpStatus.OK.name());
		List<DataTestingKucing> data = dataTestingKucingService.getDataTestingAnjingAll();
		resp.setData(data);
		return ResponseEntity.ok().body(resp);
	}
	
}
