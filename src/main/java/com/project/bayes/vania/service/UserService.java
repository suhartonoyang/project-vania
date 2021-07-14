package com.project.bayes.vania.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.bayes.vania.model.User;
import com.project.bayes.vania.repo.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public User registerUser(User newUser) {
		newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
		return userRepository.save(newUser);
	}
	
	public User loginUser(String username, String password) {
		User user = userRepository.findByUsername(username);
		if (user==null) 
			return null;

		Boolean isPasswordMatches = bCryptPasswordEncoder.matches(password, user.getPassword());
		if (!isPasswordMatches)
			return null;
		
		return user;
	}
	
	public User getUserByUsername(String username) {
		User user = userRepository.findByUsername(username);
		if (user==null) 
			return null;
		
		return user;
	}
}
