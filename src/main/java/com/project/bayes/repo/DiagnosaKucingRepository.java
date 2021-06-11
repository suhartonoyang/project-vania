package com.project.bayes.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.bayes.model.DiagnosaKucing;

@Repository
public interface DiagnosaKucingRepository extends CrudRepository<DiagnosaKucing, Long>{

}
