package com.project.bayes.vania.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bayes.vania.model.DataTestingKucing;
import com.project.bayes.vania.repo.DataTestingKucingRepository;

@Service
public class DataTestingKucingService {
	@Autowired
	private DataTestingKucingRepository dataTestingKucingRepository;

	public List<DataTestingKucing> getDataAll() {
		return StreamSupport.stream(dataTestingKucingRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());
	}

	public DataTestingKucing saveData(DataTestingKucing data) {
		return dataTestingKucingRepository.save(data);
	}
	
	public DataTestingKucing getDataById(Integer id) {
		return dataTestingKucingRepository.findById(id).orElse(null);
	}
	
	public void deleteData(Integer id) {
		dataTestingKucingRepository.deleteById(id);
	}
}
