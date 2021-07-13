package com.ramesh.shopping.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class OrderDetails {
    private List<Products> products;
    private double total;
}

