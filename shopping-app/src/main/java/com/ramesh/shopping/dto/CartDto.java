package com.ramesh.shopping.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CartDto {
	
	private long productId;
	private double total;

}
