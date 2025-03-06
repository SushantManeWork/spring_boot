package com.shoppy.Shoppy.controller;

import com.shoppy.Shoppy.DTOs.forCreate.OrderStatusDTOForCreate;
import com.shoppy.Shoppy.DTOs.forDisplay.OrderStatusDTOForDisplay;
import com.shoppy.Shoppy.services.OrderStatusService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/papi/orderStatus")
public class OrderStatusController {

    @Autowired
    private OrderStatusService _orderStatusService;

    @GetMapping
    public ResponseEntity<List<OrderStatusDTOForDisplay>> getAllOrderStatus() {
        return ResponseEntity.ok(_orderStatusService.findAll());
    }

    @GetMapping("/{orderStatusId}")
    public ResponseEntity<OrderStatusDTOForDisplay> getOrderStatus(@PathVariable(name = "orderStatusId") final Integer orderStatusId) {
        OrderStatusDTOForDisplay orderStatusDTO=_orderStatusService.get(orderStatusId);

        if (orderStatusDTO==null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(orderStatusDTO);
    }

    @PostMapping
    public ResponseEntity<OrderStatusDTOForDisplay> createOrderStatus(@RequestBody @Valid final OrderStatusDTOForCreate orderStatusDTO) {
        final OrderStatusDTOForDisplay orderStatusDTO1 = _orderStatusService.create(orderStatusDTO);

        if (orderStatusDTO1==null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.status(HttpStatus.CREATED).body(orderStatusDTO1);
    }

    @PutMapping("/{orderStatusId}")
    public ResponseEntity<OrderStatusDTOForDisplay> updateOrderStatus(@PathVariable(name = "orderStatusId") final Integer orderStatusId,
                                                @RequestBody @Valid final OrderStatusDTOForCreate orderStatusDTO) {
        OrderStatusDTOForDisplay orderStatusDTO1=_orderStatusService.update(orderStatusId, orderStatusDTO);

        if (orderStatusDTO1==null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(orderStatusDTO1);
    }

    @DeleteMapping("/{orderStatusId}")
    public ResponseEntity<Void> deleteOrderStatus(@PathVariable(name = "orderStatusId") final Integer orderStatusId) {
        _orderStatusService.delete(orderStatusId);
        return ResponseEntity.noContent().build();
    }
}
