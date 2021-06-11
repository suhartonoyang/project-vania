package com.project.bayes.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bayes.model.DataBayes;
import com.project.bayes.repo.DataBayesRepository;

@Service
public class DataBayesService {
	@Autowired
	private DataBayesRepository dataBayesRepository;
	
	public List<DataBayes> getDataBayesAll(){
		return StreamSupport.stream(dataBayesRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}
}
