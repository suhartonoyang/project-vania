package com.project.bayes.vania.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.bayes.vania.bean.Request;
import com.project.bayes.vania.bean.Response;
import com.project.bayes.vania.bean.Result;
import com.project.bayes.vania.model.DataLatihAnjing;
import com.project.bayes.vania.model.DataLatihKucing;
import com.project.bayes.vania.service.BayesDiagnosaAnjingService;
import com.project.bayes.vania.service.BayesDiagnosaKucingService;
import com.project.bayes.vania.service.BayesNaiveService;
import com.project.bayes.vania.service.DataLatihAnjingService;
import com.project.bayes.vania.service.DataLatihKucingService;

@RestController
@RequestMapping("/api/bayes")
@CrossOrigin
public class DataLatihController {

	@Autowired
	private DataLatihAnjingService dataLatihAnjingService;

	@Autowired
	private DataLatihKucingService dataLatihKucingService;

	@GetMapping("/data-latih-anjing")
	public ResponseEntity<Response> getDataLatihAnjingAll() {
		Response resp = new Response();
		resp.setCode(String.valueOf(HttpStatus.OK.value()));
		resp.setMessage(HttpStatus.OK.name());
		List<DataLatihAnjing> data = dataLatihAnjingService.getDataLatihAnjingAll();
		resp.setData(data);
		return ResponseEntity.ok().body(resp);
	}
	
	@GetMapping("/data-latih-anjing/{id}")
	public ResponseEntity<Response> getDataLatihAnjingById(@PathVariable Integer id) {
		Response resp = new Response();
		resp.setCode(String.valueOf(HttpStatus.OK.value()));
		resp.setMessage(HttpStatus.OK.name());
		List<DataLatihAnjing> data = dataLatihAnjingService.getDataLatihAnjingById(id);
		resp.setData(data);
		return ResponseEntity.ok().body(resp);
	}

	@PostMapping("/data-latih-anjing")
	public ResponseEntity<Response> saveDataLatihAnjing(@RequestBody DataLatihAnjing data) {
		Response resp = new Response();
		resp.setCode(String.valueOf(HttpStatus.CREATED.value()));
		resp.setMessage("Data berhasil disimpan!");
		DataLatihAnjing newData = dataLatihAnjingService.saveData(data);
		resp.setData(Arrays.asList(newData));
		return ResponseEntity.ok().body(resp);
	}

	@GetMapping("/data-latih-kucing")
	public ResponseEntity<Response> getDataLatihKucingAll() {
		Response resp = new Response();
		resp.setCode(String.valueOf(HttpStatus.OK.value()));
		resp.setMessage(HttpStatus.OK.name());
		List<DataLatihKucing> data = dataLatihKucingService.getDataLatihKucingAll();
		resp.setData(data);
		return ResponseEntity.ok().body(resp);
	}
	
	@GetMapping("/data-latih-kucing/{id}")
	public ResponseEntity<Response> getDataLatihKucingById(@PathVariable Integer id) {
		Response resp = new Response();
		resp.setCode(String.valueOf(HttpStatus.OK.value()));
		resp.setMessage(HttpStatus.OK.name());
		List<DataLatihKucing> data = dataLatihKucingService.getDataLatihKucingById(id);
		resp.setData(data);
		return ResponseEntity.ok().body(resp);
	}

	@PostMapping("/data-latih-kucing")
	public ResponseEntity<Response> saveDataLatihKucing(@RequestBody DataLatihKucing data) {
		Response resp = new Response();
		resp.setCode(String.valueOf(HttpStatus.CREATED.value()));
		resp.setMessage("Data berhasil disimpan!");
		DataLatihKucing newData = dataLatihKucingService.saveData(data);
		resp.setData(Arrays.asList(newData));
		return ResponseEntity.ok().body(resp);
	}
}
