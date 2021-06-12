package com.project.bayes.vania.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.bayes.vania.model.DataLatihKucing;

@Repository
public interface DataLatihKucingRepository extends CrudRepository<DataLatihKucing, Integer>{

}
