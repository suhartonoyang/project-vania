package com.project.bayes.vania.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bayes.vania.model.DiagnosaKucing;
import com.project.bayes.vania.repo.DiagnosaKucingRepository;

@Service
public class DiagnosaKucingService {
	@Autowired
	private DiagnosaKucingRepository diagnosaKucingRepository;

	public List<DiagnosaKucing> getDiagnosaKucingAll() {
		return StreamSupport.stream(diagnosaKucingRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());
	}

	public void saveData(DiagnosaKucing data) {
		diagnosaKucingRepository.save(data);
	}
}
