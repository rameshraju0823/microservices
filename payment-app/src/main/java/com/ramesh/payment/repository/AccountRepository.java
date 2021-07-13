package com.ramesh.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramesh.payment.entity.Account;
import com.ramesh.payment.entity.User;

public interface AccountRepository extends JpaRepository<Account, Long>{
	
	Account findByUser(User user);


}
