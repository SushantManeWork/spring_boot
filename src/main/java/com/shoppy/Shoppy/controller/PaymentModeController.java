package com.shoppy.Shoppy.controller;

import com.shoppy.Shoppy.DTOs.forCreate.PaymentModeDTOForCreate;
import com.shoppy.Shoppy.DTOs.forDisplay.PaymentModeDTOForDisplay;
import com.shoppy.Shoppy.services.PaymentModeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/papi/paymentMode")
public class PaymentModeController {

    @Autowired
    private PaymentModeService paymentModeService;

    @GetMapping
    public ResponseEntity<List<PaymentModeDTOForDisplay>> getAllPaymentMode() {
        return ResponseEntity.ok(paymentModeService.findAll());
    }

    @GetMapping("/{paymentModeId}")
    public ResponseEntity<PaymentModeDTOForDisplay> getPaymentMode(@PathVariable(name = "paymentModeId") final Integer paymentModeId) {
        PaymentModeDTOForDisplay paymentModeDTO=paymentModeService.get(paymentModeId);
        if (paymentModeDTO==null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(paymentModeDTO);
    }

    @PostMapping
    public ResponseEntity<PaymentModeDTOForDisplay> createPaymentMode(@RequestBody @Valid final PaymentModeDTOForCreate paymentModeDTO) {
        final PaymentModeDTOForDisplay paymentModeDTO1 = paymentModeService.create(paymentModeDTO);
        if (paymentModeDTO1==null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentModeDTO1);
    }

    @PutMapping("/{paymentModeId}")
    public ResponseEntity<PaymentModeDTOForDisplay> updatePaymentMode(@PathVariable(name = "paymentModeId") final Integer paymentModeId,
                                                @RequestBody @Valid final PaymentModeDTOForCreate paymentModeDTO) {
        PaymentModeDTOForDisplay paymentModeDTO1=paymentModeService.update(paymentModeId, paymentModeDTO);
        if (paymentModeDTO1==null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(paymentModeDTO1);
    }

    @DeleteMapping("/{paymentModeId}")
    public ResponseEntity<Void> deletePaymentMode(@PathVariable(name = "paymentModeId") final Integer paymentModeId) {
        paymentModeService.delete(paymentModeId);
        return ResponseEntity.noContent().build();
    }
}
