package com.shoppy.Shoppy.DTOs.forRequest;

import com.shoppy.Shoppy.entity.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderStatusDTOForCreate {

    @NotNull
    private String orderStatus;

    public static OrderStatus mapToEntity(OrderStatusDTOForCreate orderStatusDTO, OrderStatus orderStatus1){
        orderStatus1.setOrderStatus(orderStatusDTO.orderStatus);
        return orderStatus1;
    }
}
