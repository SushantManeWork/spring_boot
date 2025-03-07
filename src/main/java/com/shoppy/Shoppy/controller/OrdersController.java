package com.shoppy.Shoppy.controller;

import com.shoppy.Shoppy.DTOs.forRequest.OrderDTOForCreate;
import com.shoppy.Shoppy.DTOs.forResponse.DetailedOrder;
import com.shoppy.Shoppy.DTOs.forResponse.OrderDTOForDisplay;
import com.shoppy.Shoppy.services.OrdersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/papi/orders")
@CrossOrigin
public class OrdersController {

    @Autowired
    private OrdersService _ordersService;

    @GetMapping
    public ResponseEntity<List<OrderDTOForDisplay>> getAllOrders() {
        return ResponseEntity.ok(_ordersService.findAll());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<DetailedOrder>> getOrderByUserId(@PathVariable(name = "userId") final Integer userId) {
        List<DetailedOrder> orderDTO=_ordersService.get(userId);

        if (orderDTO==null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(orderDTO);
    }

    @PostMapping
    public ResponseEntity<OrderDTOForDisplay> createOrder(@RequestBody @Valid final OrderDTOForCreate orderDTO) {
        final OrderDTOForDisplay orderDTO1 = _ordersService.create(orderDTO);

        if (orderDTO1==null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.status(HttpStatus.CREATED).body(orderDTO1);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderDTOForDisplay> updateOrder(@PathVariable(name = "orderId") final Integer orderId,
                                                @RequestBody @Valid final OrderDTOForCreate orderDTO) {
        OrderDTOForDisplay orderDTO1=_ordersService.update(orderId, orderDTO);

        if (orderDTO1==null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(orderDTO1);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable(name = "orderId") final Integer orderId) {
        _ordersService.delete(orderId);
        return ResponseEntity.noContent().build();
    }
}
