package com.project.bayes.vania.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.bayes.vania.model.DataTestingKucing;

@Repository
public interface DataTestingKucingRepository extends CrudRepository<DataTestingKucing, Integer>{

}
