package com.shoppy.Shoppy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "OrderStatus")
@Getter
@Setter
public class OrderStatus {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderStatusId ;

    @Column(nullable = false, length = 30)
    private String orderStatus;

    @OneToMany(mappedBy = "orderStatus")
    private List<Orders> orders;

    public OrderStatus(){}

    public OrderStatus(Integer orderStatusId){
        this.orderStatusId=orderStatusId;
    }
}
