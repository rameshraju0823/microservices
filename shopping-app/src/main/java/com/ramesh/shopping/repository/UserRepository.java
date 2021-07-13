package com.ramesh.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramesh.shopping.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByEmail(String email);
	boolean existsUserByEmail (String email);

}