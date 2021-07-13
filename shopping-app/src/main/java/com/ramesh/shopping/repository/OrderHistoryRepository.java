package com.ramesh.shopping.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramesh.shopping.entity.OrderSummery;


public interface OrderHistoryRepository extends JpaRepository<OrderSummery, Long>{
	
	List<OrderSummery> findByOrderedDateBetween(LocalDate fromDate, LocalDate toDate);

}
