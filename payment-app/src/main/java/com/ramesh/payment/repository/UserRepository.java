package com.ramesh.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramesh.payment.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByEmail(String email);
	boolean existsUserByEmail (String email);

}
