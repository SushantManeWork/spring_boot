package com.shoppy.Shoppy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Orders")
@Getter
@Setter
public class Orders {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId ;

    @ManyToOne(fetch = FetchType.LAZY)
    private Products product ;

    @ManyToOne(fetch = FetchType.LAZY)
    private Users user ;

    @Column(nullable = false)
    private Integer quantity ;

    @Column(nullable = false)
    private Integer pricePerUnit ;

    @Column(nullable = false)
    private Integer totalAmount ;

    @ManyToOne(fetch = FetchType.LAZY)
    private DeliveryTypes deliveryType ;

    @ManyToOne(fetch = FetchType.LAZY)
    private PaymentMode paymentMode;

    @ManyToOne(fetch = FetchType.LAZY)
    private OrderStatus orderStatus ;

    public Orders(){}

    public Orders(Integer orderId){
        this.orderId=orderId;
    }
}
