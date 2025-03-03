package com.shoppy.Shoppy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "PaymentMode")
@Getter
@Setter
public class PaymentMode {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentModeId ;

    @Column(nullable = false, length = 30)
    private String paymentMode ;

    @Column(nullable = false, length = 30)
    private String paymentSubMode;

    @OneToMany(mappedBy = "paymentMode")
    private List<Orders> orders;

    public PaymentMode(){}

    public PaymentMode(Integer paymentModeId){
        this.paymentModeId=paymentModeId;
    }
}
