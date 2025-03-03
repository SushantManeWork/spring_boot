package com.shoppy.Shoppy.DTOs.forDisplay;

import com.shoppy.Shoppy.entity.Orders;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetailedOrder {

    private Integer orderId ;

    private String productName ;

    private String productType ;

    private Integer quantity ;

    private Integer pricePerUnit ;

    private Integer totalAmount ;

    private String deliveryType ;

    private String paymentMode;

    private String orderStatus ;

    public static DetailedOrder mapToDTO(Orders orders){
        DetailedOrder detailedOrder=new DetailedOrder();
        detailedOrder.setOrderId(orders.getOrderId());
        detailedOrder.setProductName(orders.getProduct().getProductName());
        detailedOrder.setProductType(orders.getProduct().getProductTypes().getProductTypeName());
        detailedOrder.setQuantity(orders.getQuantity());
        detailedOrder.setPricePerUnit(orders.getPricePerUnit());
        detailedOrder.setTotalAmount(orders.getTotalAmount());
        detailedOrder.setDeliveryType(orders.getDeliveryType().getDeliveryType());
        detailedOrder.setPaymentMode(orders.getPaymentMode().getPaymentMode()+"-"+orders.getPaymentMode().getPaymentSubMode());
        detailedOrder.setOrderStatus(orders.getOrderStatus().getOrderStatus());
        return detailedOrder;
    }
}
