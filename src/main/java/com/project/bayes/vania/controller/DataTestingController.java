package com.project.bayes.vania.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		List<DataTestingAnjing> data = dataTestingAnjingService.getDataAll();
		resp.setData(data);
		return ResponseEntity.ok().body(resp);
	}
	
	@DeleteMapping("/data-testing-anjing/{id}")
	public ResponseEntity<Response> deleteDataTestingAnjing(@PathVariable Integer id) {
		Response resp = new Response();
		DataTestingAnjing checkData = dataTestingAnjingService.getDataById(id);
		if (checkData == null) {
			resp.setCode(String.valueOf(HttpStatus.NOT_FOUND.value()));
			resp.setMessage("Data not found");
			return ResponseEntity.ok().body(resp);
		}	
		
		dataTestingAnjingService.deleteData(id);
		resp.setCode(String.valueOf(HttpStatus.OK.value()));
		resp.setMessage("Data is successfully deleted");
		return ResponseEntity.ok().body(resp);
	}
	
	@GetMapping("/data-testing-kucing")
	public ResponseEntity<Response> getDataTestingKucingAll() {
		Response resp = new Response();
		resp.setCode(String.valueOf(HttpStatus.OK.value()));
		resp.setMessage(HttpStatus.OK.name());
		List<DataTestingKucing> data = dataTestingKucingService.getDataAll();
		resp.setData(data);
		return ResponseEntity.ok().body(resp);
	}
	
	@DeleteMapping("/data-testing-kucing/{id}")
	public ResponseEntity<Response> deleteDataTestingKucing(@PathVariable Integer id) {
		Response resp = new Response();
		DataTestingKucing checkData = dataTestingKucingService.getDataById(id);
		if (checkData == null) {
			resp.setCode(String.valueOf(HttpStatus.NOT_FOUND.value()));
			resp.setMessage("Data not found");
			return ResponseEntity.ok().body(resp);
		}	
		
		dataTestingKucingService.deleteData(id);
		resp.setCode(String.valueOf(HttpStatus.OK.value()));
		resp.setMessage("Data is successfully deleted");
		return ResponseEntity.ok().body(resp);
	}
	
}
