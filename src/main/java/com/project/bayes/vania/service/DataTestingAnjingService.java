package com.project.bayes.vania.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bayes.vania.model.DataTestingAnjing;
import com.project.bayes.vania.repo.DataTestingAnjingRepository;

@Service
public class DataTestingAnjingService {
	@Autowired
	private DataTestingAnjingRepository dataTestingAnjingRepository;

	public List<DataTestingAnjing> getDataTestingAnjingAll() {
		return StreamSupport.stream(dataTestingAnjingRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());
	}

	public DataTestingAnjing saveData(DataTestingAnjing data) {
		return dataTestingAnjingRepository.save(data);
	}
}
