package com.project.bayes.vania.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.project.bayes.vania.model.DataLatihAnjing;

@Repository
public interface DataLatihAnjingRepository
		extends CrudRepository<DataLatihAnjing, Integer>, JpaRepository<DataLatihAnjing, Integer> {
}
