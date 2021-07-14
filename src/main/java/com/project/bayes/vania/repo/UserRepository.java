package com.project.bayes.vania.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.bayes.vania.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	public User findByUsername(String username);
	
}
