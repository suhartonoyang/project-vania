package com.project.bayes.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.bayes.model.DataBayes;

@Repository
public interface DataBayesRepository extends CrudRepository<DataBayes, Long>{

}
