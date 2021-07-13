package com.ramesh.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramesh.shopping.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{

	
}
