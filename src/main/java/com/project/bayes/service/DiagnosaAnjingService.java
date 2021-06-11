package com.project.bayes.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bayes.model.DiagnosaAnjing;
import com.project.bayes.repo.DiagnosaAnjingRepository;

@Service
public class DiagnosaAnjingService {
	@Autowired
	private DiagnosaAnjingRepository diagnosaAnjingRepository;

	public List<DiagnosaAnjing> getDataDiagnosaAnjingAll() {
		return StreamSupport.stream(diagnosaAnjingRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());
	}

	public void saveData(DiagnosaAnjing data) {
		diagnosaAnjingRepository.save(data);
	}
}
