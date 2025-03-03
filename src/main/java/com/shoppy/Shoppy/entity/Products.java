package com.shoppy.Shoppy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Products")
@Getter
@Setter
public class Products {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId ;

    @Column(nullable = false, length = 30)
    private String productName ;

    @Column(nullable = false)
    private Integer price ;

    @Column(nullable = false)
    private Integer quantity ;

    @Column(nullable = false)
    private Integer sellQuantity ;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProductTypes productTypes;

    @OneToMany(mappedBy = "product")
    private List<Orders> orders;

    public Products(){}

    public Products(Integer productId){
        this.productId=productId;
    }
}
