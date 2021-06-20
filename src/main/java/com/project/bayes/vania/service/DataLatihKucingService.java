package com.project.bayes.vania.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Optionals;
import org.springframework.stereotype.Service;

import com.google.common.base.Optional;
import com.project.bayes.vania.model.DataLatihKucing;
import com.project.bayes.vania.repo.DataLatihKucingRepository;

@Service
public class DataLatihKucingService {
	@Autowired
	private DataLatihKucingRepository dataLatihKucingRepository;

	public List<DataLatihKucing> getDataLatihKucingAll() {
		return StreamSupport.stream(dataLatihKucingRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());
	}

	public List<DataLatihKucing> getDataLatihKucingById(Integer id) {
		return Collections
				.singletonList(Optional.fromNullable(dataLatihKucingRepository.findById(id).orElse(null)).get());
	}

	public DataLatihKucing saveData(DataLatihKucing data) {
		return dataLatihKucingRepository.save(data);
	}
	
	public String deleteDataById(Integer id) {
		dataLatihKucingRepository.deleteById(id);
		return "OK";		
	}
}
