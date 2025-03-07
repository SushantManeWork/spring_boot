package com.shoppy.Shoppy.services;

import com.shoppy.Shoppy.DTOs.forRequest.OrderDTOForCreate;
import com.shoppy.Shoppy.DTOs.forResponse.DetailedOrder;
import com.shoppy.Shoppy.DTOs.forResponse.OrderDTOForDisplay;
import com.shoppy.Shoppy.entity.*;
import com.shoppy.Shoppy.exception.ValidationException;
import com.shoppy.Shoppy.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrdersService {

    @Autowired
    private OrdersRepository _ordersRepository;
    @Autowired
    private ProductsRepository _productsRepository;
    @Autowired
    private UsersRepository _usersRepository;
    @Autowired
    private DeliveryTypesRepository _deliveryTypesRepository;
    @Autowired
    private PaymentModeRepository _paymentModeRepository;
    @Autowired
    private OrderStatusRepository _orderStatusRepository;

    public List<OrderDTOForDisplay> findAll(){
        List<Orders> orders=_ordersRepository.findAll();
        return orders.stream().map(OrderDTOForDisplay::mapToDto).toList();
    }

    public List<DetailedOrder> get(Integer userId){
        List<String> exception=new ArrayList<>();

        if (userId<=0)
            exception.add("Id is wrong");
        List<DetailedOrder> orderDTO=_ordersRepository.findByUserUserId(userId).map(DetailedOrder::mapToDTO).stream().toList();

        if (!exception.isEmpty())
            throw new ValidationException(String.join(",\n", exception));
        return orderDTO;
    }

    public OrderDTOForDisplay create(OrderDTOForCreate orderDTO){
        List<String> exception=new ArrayList<>();

        if (orderDTO.getQuantity()<=0 || orderDTO.getPricePerUnit()<=0)
            exception.add("Invalid details");
        Products products=_productsRepository.findById(orderDTO.getProductId()).orElse(null);

        if (products==null)
            exception.add("Product not found");
        if (products!=null && products.getQuantity()<orderDTO.getQuantity())
            exception.add("Ordered quantity is not available");
        Users users=_usersRepository.findById(orderDTO.getUserId()).orElse(null);

        if (users==null)
            exception.add("user not found");
        DeliveryTypes deliveryTypes=_deliveryTypesRepository.findById(orderDTO.getDeliveryTypeId()).orElse(null);

        if (deliveryTypes==null)
            exception.add("Delivery type not found");
        PaymentMode paymentMode=_paymentModeRepository.findById(orderDTO.getPaymentModeId()).orElse(null);

        if (paymentMode==null)
            exception.add("Payment mode not found");
        OrderStatus orderStatus=_orderStatusRepository.findById(orderDTO.getOrderStatusId()).orElse(null);

        if (orderStatus==null)
            exception.add("Order status not found");

        if (!exception.isEmpty())
            throw new ValidationException(String.join(",\n", exception));
        
        products.setQuantity(products.getQuantity()-orderDTO.getQuantity());
        products.setSellQuantity(products.getSellQuantity()+ orderDTO.getQuantity());
        _productsRepository.save(products);

        Orders orders=OrderDTOForCreate.mapToEntity(orderDTO,new Orders());
        return OrderDTOForDisplay.mapToDto(_ordersRepository.save(orders));
    }

    public OrderDTOForDisplay update(Integer orderId,OrderDTOForCreate orderDTO){
        List<String> exception=new ArrayList<>();

        if (orderDTO.getQuantity()<=0 || orderDTO.getPricePerUnit()<=0)
            exception.add("Invalid details");
        Orders orders=_ordersRepository.findById(orderId).orElse(null);

        if (orders==null)
            exception.add("Order not found for provided id");
        Products products=_productsRepository.findById(orderDTO.getProductId()).orElse(null);

        if (products==null)
            exception.add("Product not found");

        if (products!=null && products.getQuantity()<orderDTO.getQuantity())
            exception.add("Ordered quantity is not available");
        Users users=_usersRepository.findById(orderDTO.getUserId()).orElse(null);

        if (users==null)
            exception.add("user not found");
        DeliveryTypes deliveryTypes=_deliveryTypesRepository.findById(orderDTO.getDeliveryTypeId()).orElse(null);

        if (deliveryTypes==null)
            exception.add("Delivery type not found");
        PaymentMode paymentMode=_paymentModeRepository.findById(orderDTO.getPaymentModeId()).orElse(null);

        if (paymentMode==null)
            exception.add("Payment mode not found");
        OrderStatus orderStatus=_orderStatusRepository.findById(orderDTO.getOrderStatusId()).orElse(null);

        if (orderStatus==null)
            exception.add("Order status not found");

        if (!exception.isEmpty())
            throw new ValidationException(String.join(",\n", exception));

        if (orderStatus.getOrderStatus().equalsIgnoreCase("Cancelled")){
            products.setQuantity(products.getQuantity()+orderDTO.getQuantity());
            products.setSellQuantity(products.getSellQuantity()-orderDTO.getQuantity());
            _productsRepository.save(products);
        }

        OrderDTOForCreate.mapToEntity(orderDTO,orders);
        return OrderDTOForDisplay.mapToDto(_ordersRepository.save(orders));
    }

    public void delete(Integer orderId){
        _ordersRepository.deleteById(orderId);
    }
}
