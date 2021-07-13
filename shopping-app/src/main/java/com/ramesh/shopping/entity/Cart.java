package com.ramesh.shopping.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Entity
@Table
@Builder
@Data
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long cartId;
	@ManyToMany
	@JoinColumn
	private List<Product> products;
	private double total;
	public Cart(long cartId, List<Product> products, double total) {
		super();
		this.cartId = cartId;
		this.products = products;
		this.total = total;
	}
	public Cart() {
	}
	
	

}
