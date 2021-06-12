package com.project.bayes.vania.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.bayes.vania.model.DataBayes;

@Repository
public interface DataBayesRepository extends CrudRepository<DataBayes, Long>{

}
