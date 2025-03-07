package com.shoppy.Shoppy.DTOs.forResponse;

import com.shoppy.Shoppy.entity.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDTOForDisplay {

    private Integer orderId ;

    private Integer product ;

    private Integer user ;

    private Integer quantity ;

    private Integer pricePerUnit ;

    private Integer totalAmount ;

    private Integer deliveryType ;

    private Integer paymentMode;

    private Integer orderStatus ;

    public static OrderDTOForDisplay mapToDto(Orders orders){
        OrderDTOForDisplay orderDTO=new OrderDTOForDisplay();
        orderDTO.setOrderId(orders.getOrderId());
        orderDTO.setProduct(orders.getProduct()==null?null:orders.getProduct().getProductId());
        orderDTO.setUser(orders.getUser()==null?null:orders.getUser().getUserId());
        orderDTO.setQuantity(orders.getQuantity());
        orderDTO.setPricePerUnit(orders.getPricePerUnit());
        orderDTO.setTotalAmount(orders.getTotalAmount());
        orderDTO.setDeliveryType(orders.getDeliveryType()==null?null:orders.getDeliveryType().getDeliveryTypeId());
        orderDTO.setPaymentMode(orders.getPaymentMode()==null?null:orders.getPaymentMode().getPaymentModeId());
        orderDTO.setOrderStatus(orders.getOrderStatus()==null?null:orders.getOrderStatus().getOrderStatusId());
        return orderDTO;
    }

}
