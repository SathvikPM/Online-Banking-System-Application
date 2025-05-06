package com.tap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tap.model.User;
import com.tap.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repo; 
	
	private BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
	
	public void saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		repo.save(user);
	}
	
	
	
	public User getUser(String email,String rawPassword) {
		User user=repo.findByEmail(email);
		if(user!=null && passwordEncoder.matches(rawPassword, user.getPassword())) {
			return user;
		}
		return null;
	}
	
	public boolean emailExists(String email) {
		return repo.existsByEmail(email);
	}
}
