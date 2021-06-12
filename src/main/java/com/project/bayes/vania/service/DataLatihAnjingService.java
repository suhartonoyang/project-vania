package com.project.bayes.vania.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Optional;
import com.project.bayes.vania.model.DataLatihAnjing;
import com.project.bayes.vania.model.DataLatihKucing;
import com.project.bayes.vania.repo.DataLatihAnjingRepository;

@Service
public class DataLatihAnjingService {
	@Autowired
	private DataLatihAnjingRepository dataLatihAnjingRepository;

	public List<DataLatihAnjing> getDataLatihAnjingAll() {
		return StreamSupport.stream(dataLatihAnjingRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());
	}

	public List<DataLatihAnjing> getDataLatihAnjingById(Integer id) {
		return Collections
				.singletonList(Optional.fromNullable(dataLatihAnjingRepository.findById(id).orElse(null)).get());
	}

	public DataLatihAnjing saveData(DataLatihAnjing data) {
		return dataLatihAnjingRepository.save(data);
	}
}
