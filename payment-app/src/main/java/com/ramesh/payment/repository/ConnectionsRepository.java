package com.ramesh.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramesh.payment.entity.BeneficiaryConnections;

public interface ConnectionsRepository extends JpaRepository<BeneficiaryConnections, Long>{

}
