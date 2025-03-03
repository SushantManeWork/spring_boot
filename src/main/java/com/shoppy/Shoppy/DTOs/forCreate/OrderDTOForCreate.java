package com.shoppy.Shoppy.DTOs.forCreate;

import com.shoppy.Shoppy.entity.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDTOForCreate {

    private Integer productId ;

    private Integer userId ;

    private Integer quantity ;

    private Integer pricePerUnit ;

    private Integer totalAmount ;

    private Integer deliveryTypeId ;

    private Integer paymentModeId;

    private Integer orderStatusId ;

    public static Orders mapToEntity(OrderDTOForCreate orderDTO, Orders orders){
        orders.setProduct(orderDTO.getProductId()==null?null:new Products(orderDTO.getProductId()));
        orders.setUser(orderDTO.getUserId()==null?null:new Users(orderDTO.getUserId()));
        orders.setQuantity(orderDTO.getQuantity());
        orders.setPricePerUnit(orderDTO.getPricePerUnit());
        orders.setTotalAmount(orderDTO.getPricePerUnit()*orderDTO.getQuantity());
        orders.setDeliveryType(orderDTO.getDeliveryTypeId()==null?null:new DeliveryTypes(orderDTO.getDeliveryTypeId()));
        orders.setPaymentMode(orderDTO.getPaymentModeId()==null?null:new PaymentMode(orderDTO.getPaymentModeId()));
        orders.setOrderStatus(orderDTO.getOrderStatusId()==null?null:new OrderStatus(orderDTO.getOrderStatusId()));
        return orders;
    }
}
