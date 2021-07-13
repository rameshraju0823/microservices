package com.ramesh.shopping.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Products {
    private String brand;
    private String modelName;
    private double price;
}
