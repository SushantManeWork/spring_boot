package com.shoppy.Shoppy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "DeliveryType")
@Getter
@Setter
public class DeliveryTypes {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer deliveryTypeId;

    @Column(nullable = false, length = 30)
    private String deliveryType ;

    @OneToMany(mappedBy = "deliveryType")
    private List<Orders> orders;

    public DeliveryTypes(){

    }

    public DeliveryTypes(Integer deliveryTypeId){
        this.deliveryTypeId=deliveryTypeId;
    }
}
