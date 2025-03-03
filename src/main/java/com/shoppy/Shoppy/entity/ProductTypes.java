package com.shoppy.Shoppy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "ProductTypes")
@Getter
@Setter
public class ProductTypes {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productTypeId ;

    @Column(nullable = false, length = 30)
    private String productTypeName ;

    @Column(nullable = false)
    private Boolean isActive ;

    @OneToMany(mappedBy = "productTypes")
    private List<Products> products;

    public ProductTypes(){}

    public ProductTypes(Integer productTypeId){
        this.productTypeId=productTypeId;
    }
}
