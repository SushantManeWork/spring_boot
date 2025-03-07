package com.shoppy.Shoppy.DTOs.forResponse;

import com.shoppy.Shoppy.entity.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderStatusDTOForDisplay {

    private Integer orderStatusId ;

    @NotNull
    private String orderStatus;

    public static OrderStatusDTOForDisplay mapToDto(OrderStatus orderStatus){
        OrderStatusDTOForDisplay orderStatusDTO=new OrderStatusDTOForDisplay();
        orderStatusDTO.setOrderStatus(orderStatus.getOrderStatus());
        orderStatusDTO.setOrderStatusId(orderStatus.getOrderStatusId());
        return orderStatusDTO;
    }

}
