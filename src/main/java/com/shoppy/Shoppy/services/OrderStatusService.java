package com.shoppy.Shoppy.services;

import com.shoppy.Shoppy.DTOs.forRequest.OrderStatusDTOForCreate;
import com.shoppy.Shoppy.DTOs.forResponse.OrderStatusDTOForDisplay;
import com.shoppy.Shoppy.entity.OrderStatus;
import com.shoppy.Shoppy.exception.ValidationException;
import com.shoppy.Shoppy.repository.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderStatusService {

    @Autowired
    private OrderStatusRepository _orderStatusRepository;

    public List<OrderStatusDTOForDisplay> findAll(){
        List<OrderStatus> orderStatuses=_orderStatusRepository.findAll();
        return orderStatuses.stream().map(OrderStatusDTOForDisplay::mapToDto).toList();
    }

    public OrderStatusDTOForDisplay get(Integer orderStatusId){
        List<String> exception=new ArrayList<>();

        if (orderStatusId<=0)
            exception.add("Id is wrong");
        OrderStatusDTOForDisplay orderStatusDTO=_orderStatusRepository.findById(orderStatusId).map(OrderStatusDTOForDisplay::mapToDto).orElse(null);

        if (orderStatusDTO==null)
            exception.add("Order status not found");
        if (!exception.isEmpty())
            throw new ValidationException(String.join(",\n", exception));
        return orderStatusDTO;
    }

    public OrderStatusDTOForDisplay create(OrderStatusDTOForCreate orderStatusDTO){
        List<String> exception=new ArrayList<>();

        if (orderStatusDTO.getOrderStatus().isBlank())
            exception.add("Invalid details");
        if (!exception.isEmpty())
            throw new ValidationException(String.join(",\n", exception));

        OrderStatus orderStatus=OrderStatusDTOForCreate.mapToEntity(orderStatusDTO,new OrderStatus());
        return  OrderStatusDTOForDisplay.mapToDto(_orderStatusRepository.save(orderStatus));
    }

    public OrderStatusDTOForDisplay update(Integer orderStatusId,OrderStatusDTOForCreate orderStatusDTO){
        List<String> exception=new ArrayList<>();

        if (orderStatusId<=0 || orderStatusDTO.getOrderStatus().isBlank())
            exception.add("Invalid details");
        OrderStatus orderStatus=_orderStatusRepository.findById(orderStatusId).orElse(null);

         if (orderStatus==null)
             exception.add("Order status not found for provided id");
         if (!exception.isEmpty())
            throw new ValidationException(String.join(",\n", exception));

        OrderStatusDTOForCreate.mapToEntity(orderStatusDTO,orderStatus);
        return OrderStatusDTOForDisplay.mapToDto(_orderStatusRepository.save(orderStatus));

    }

    public void delete(Integer orderStatusId){
        _orderStatusRepository.deleteById(orderStatusId);
    }
}
