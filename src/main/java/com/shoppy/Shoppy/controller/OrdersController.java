package com.shoppy.Shoppy.controller;

import com.shoppy.Shoppy.DTOs.forCreate.OrderDTOForCreate;
import com.shoppy.Shoppy.DTOs.forDisplay.DetailedOrder;
import com.shoppy.Shoppy.DTOs.forDisplay.OrderDTOForDisplay;
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
    private OrdersService ordersService;

    @GetMapping
    public ResponseEntity<List<OrderDTOForDisplay>> getAllOrders() {
        return ResponseEntity.ok(ordersService.findAll());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<List<DetailedOrder>> getOrder(@PathVariable(name = "orderId") final Integer orderId) {
        List<DetailedOrder> orderDTO=ordersService.get(orderId);
        if (orderDTO==null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(orderDTO);
    }

    @PostMapping
    public ResponseEntity<OrderDTOForDisplay> createOrder(@RequestBody @Valid final OrderDTOForCreate orderDTO) {
        final OrderDTOForDisplay orderDTO1 = ordersService.create(orderDTO);
        if (orderDTO1==null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.status(HttpStatus.CREATED).body(orderDTO1);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderDTOForDisplay> updateOrder(@PathVariable(name = "orderId") final Integer orderId,
                                                @RequestBody @Valid final OrderDTOForCreate orderDTO) {
        OrderDTOForDisplay orderDTO1=ordersService.update(orderId, orderDTO);
        if (orderDTO1==null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(orderDTO1);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable(name = "orderId") final Integer orderId) {
        ordersService.delete(orderId);
        return ResponseEntity.noContent().build();
    }
}
