package com.ramesh.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramesh.shopping.entity.Product;
import com.ramesh.shopping.entity.ProductType;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	List<Product> findByProductType(ProductType productType);
	
}
