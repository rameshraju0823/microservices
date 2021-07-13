package com.ramesh.shopping.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.*;

import lombok.Builder;
import lombok.Data;

@Entity
@Table
@Builder
@Data
public class OrderSummery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;
    @ManyToMany
    @JoinColumn
    private List<Product> products;
    private LocalDate orderedDate;
    private LocalTime orderedTime;
    @Enumerated(EnumType.STRING)
    private Status orderStatus;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    private double total;
    public OrderSummery(){}

    public OrderSummery(long orderId, List<Product> products, LocalDate orderedDate,
                        LocalTime orderedTime, Status orderStatus,
                        PaymentStatus paymentStatus, double total) {
        this.orderId = orderId;
        this.products = products;
        this.orderedDate = orderedDate;
        this.orderedTime = orderedTime;
        this.orderStatus = orderStatus;
        this.paymentStatus = paymentStatus;
        this.total = total;
    }

	
}
