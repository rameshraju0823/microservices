package com.ramesh.shopping.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table
@Data
@RequiredArgsConstructor
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long productId;
	@Enumerated(EnumType.STRING)
	private ProductType productType;
	private String productSubCategory;
	private String brand;
	private String modelName;
	private String dealerInfo;
	private double customerReview;
	private double price;
	
	

}
