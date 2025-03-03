package com.shoppy.Shoppy.DTOs.forCreate;

import com.shoppy.Shoppy.entity.PaymentMode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentModeDTOForCreate {

    private String paymentMode ;

    private String paymentSubMode;

    public static PaymentMode mapToEntity(PaymentModeDTOForCreate paymentModeDTO, PaymentMode paymentMode1){
        paymentMode1.setPaymentMode(paymentModeDTO.getPaymentMode());
        paymentMode1.setPaymentSubMode(paymentModeDTO.getPaymentSubMode());
        return paymentMode1;
    }
}
