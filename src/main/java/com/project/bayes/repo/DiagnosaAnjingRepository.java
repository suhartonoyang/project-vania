package com.project.bayes.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.bayes.model.DiagnosaAnjing;

@Repository
public interface DiagnosaAnjingRepository extends CrudRepository<DiagnosaAnjing, Long>{

}
