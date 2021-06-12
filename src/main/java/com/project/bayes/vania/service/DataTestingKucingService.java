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

	public List<DataTestingKucing> getDataTestingAnjingAll() {
		return StreamSupport.stream(dataTestingKucingRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());
	}

	public DataTestingKucing saveData(DataTestingKucing data) {
		return dataTestingKucingRepository.save(data);
	}
}
