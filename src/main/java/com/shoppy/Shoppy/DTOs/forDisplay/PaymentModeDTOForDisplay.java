package com.shoppy.Shoppy.DTOs.forDisplay;

import com.shoppy.Shoppy.entity.PaymentMode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentModeDTOForDisplay {

    private int paymentModeId ;

    private String paymentMode ;

    private String paymentSubMode;

    public static PaymentModeDTOForDisplay mapToDto(PaymentMode paymentMode){
        PaymentModeDTOForDisplay paymentModeDTO=new PaymentModeDTOForDisplay();
        paymentModeDTO.setPaymentModeId(paymentMode.getPaymentModeId());
        paymentModeDTO.setPaymentMode(paymentMode.getPaymentMode());
        paymentModeDTO.setPaymentSubMode(paymentMode.getPaymentSubMode());
        return paymentModeDTO;
    }

}
