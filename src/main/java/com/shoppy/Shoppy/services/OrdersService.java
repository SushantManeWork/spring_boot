package com.shoppy.Shoppy.services;

import com.shoppy.Shoppy.DTOs.forCreate.OrderDTOForCreate;
import com.shoppy.Shoppy.DTOs.forDisplay.DetailedOrder;
import com.shoppy.Shoppy.DTOs.forDisplay.OrderDTOForDisplay;
import com.shoppy.Shoppy.entity.*;
import com.shoppy.Shoppy.exception.ValidationException;
import com.shoppy.Shoppy.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private DeliveryTypesRepository deliveryTypesRepository;
    @Autowired
    private PaymentModeRepository paymentModeRepository;
    @Autowired
    private OrderStatusRepository orderStatusRepository;

    public List<OrderDTOForDisplay> findAll(){
        List<Orders> orders=ordersRepository.findAll();
        return orders.stream().map(OrderDTOForDisplay::mapToDto).toList();
    }

    public List<DetailedOrder> get(Integer orderId){
        List<String> exception=new ArrayList<>();
        if (orderId<=0)
            exception.add("Id is wrong");
        List<DetailedOrder> orderDTO=ordersRepository.findByUserUserId(orderId).map(DetailedOrder::mapToDTO).stream().toList();
//        if (orderDTO.isEmpty())
//            exception.add("Order not found for provided id");
        if (!exception.isEmpty())
            throw new ValidationException(String.join(",\n", exception));
        return orderDTO;
    }

    public OrderDTOForDisplay create(OrderDTOForCreate orderDTO){
        List<String> exception=new ArrayList<>();
        if (orderDTO.getQuantity()<=0 || orderDTO.getPricePerUnit()<=0)
            exception.add("Invalid details");
        Products products=productsRepository.findById(orderDTO.getProductId()).orElse(null);
        if (products==null)
            exception.add("Product not found");
        if (products!=null && products.getQuantity()<orderDTO.getQuantity())
            exception.add("Ordered quantity is not available");
        Users users=usersRepository.findById(orderDTO.getUserId()).orElse(null);
        if (users==null)
            exception.add("user not found");
        DeliveryTypes deliveryTypes=deliveryTypesRepository.findById(orderDTO.getDeliveryTypeId()).orElse(null);
        if (deliveryTypes==null)
            exception.add("Delivery type not found");
        PaymentMode paymentMode=paymentModeRepository.findById(orderDTO.getPaymentModeId()).orElse(null);
        if (paymentMode==null)
            exception.add("Payment mode not found");
        OrderStatus orderStatus=orderStatusRepository.findById(orderDTO.getOrderStatusId()).orElse(null);
        if (orderStatus==null)
            exception.add("Order status not found");

        if (!exception.isEmpty())
            throw new ValidationException(String.join(",\n", exception));
        
        products.setQuantity(products.getQuantity()-orderDTO.getQuantity());
        products.setSellQuantity(products.getSellQuantity()+ orderDTO.getQuantity());
        productsRepository.save(products);

        Orders orders=OrderDTOForCreate.mapToEntity(orderDTO,new Orders());
        return OrderDTOForDisplay.mapToDto(ordersRepository.save(orders));
    }

    public OrderDTOForDisplay update(Integer orderId,OrderDTOForCreate orderDTO){
        List<String> exception=new ArrayList<>();
        if (orderDTO.getQuantity()<=0 || orderDTO.getPricePerUnit()<=0)
            exception.add("Invalid details");
        Orders orders=ordersRepository.findById(orderId).orElse(null);
        if (orders==null)
            exception.add("Order not found for provided id");
        Products products=productsRepository.findById(orderDTO.getProductId()).orElse(null);
        if (products==null)
            exception.add("Product not found");
        if (products!=null && products.getQuantity()<orderDTO.getQuantity())
            exception.add("Ordered quantity is not available");
        Users users=usersRepository.findById(orderDTO.getUserId()).orElse(null);
        if (users==null)
            exception.add("user not found");
        DeliveryTypes deliveryTypes=deliveryTypesRepository.findById(orderDTO.getDeliveryTypeId()).orElse(null);
        if (deliveryTypes==null)
            exception.add("Delivery type not found");
        PaymentMode paymentMode=paymentModeRepository.findById(orderDTO.getPaymentModeId()).orElse(null);
        if (paymentMode==null)
            exception.add("Payment mode not found");
        OrderStatus orderStatus=orderStatusRepository.findById(orderDTO.getOrderStatusId()).orElse(null);
        if (orderStatus==null)
            exception.add("Order status not found");

        if (!exception.isEmpty())
            throw new ValidationException(String.join(",\n", exception));

        if (orderStatus.getOrderStatus().equalsIgnoreCase("Cancelled")){
            products.setQuantity(products.getQuantity()+orderDTO.getQuantity());
            products.setSellQuantity(products.getSellQuantity()-orderDTO.getQuantity());
            productsRepository.save(products);
        }

        OrderDTOForCreate.mapToEntity(orderDTO,orders);
        return OrderDTOForDisplay.mapToDto(ordersRepository.save(orders));
    }

    public void delete(Integer orderId){
        ordersRepository.deleteById(orderId);
    }
}
