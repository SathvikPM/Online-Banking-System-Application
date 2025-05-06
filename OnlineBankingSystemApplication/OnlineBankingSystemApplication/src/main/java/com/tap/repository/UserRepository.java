package com.tap.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tap.model.User;



public interface UserRepository extends JpaRepository< User, Integer> {
    // You can define custom query methods here if needed
	User findByEmailAndPassword(String email, String password);
	boolean existsByEmail(String email);
	User findByEmail(String email); 

}
 