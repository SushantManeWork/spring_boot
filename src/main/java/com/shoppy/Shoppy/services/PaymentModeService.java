package com.shoppy.Shoppy.services;

import com.shoppy.Shoppy.DTOs.forCreate.PaymentModeDTOForCreate;
import com.shoppy.Shoppy.DTOs.forDisplay.PaymentModeDTOForDisplay;
import com.shoppy.Shoppy.entity.PaymentMode;
import com.shoppy.Shoppy.exception.ValidationException;
import com.shoppy.Shoppy.repository.PaymentModeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentModeService {

    @Autowired
    private PaymentModeRepository _paymentModeRepository;

    public List<PaymentModeDTOForDisplay> findAll(){
        List<PaymentMode> paymentModes=_paymentModeRepository.findAll();
        return paymentModes.stream().map(PaymentModeDTOForDisplay::mapToDto).toList();
    }

    public PaymentModeDTOForDisplay get(Integer paymentModeId){
        List<String> exception=new ArrayList<>();

        if (paymentModeId<=0)
            exception.add("Id is wrong");
        PaymentModeDTOForDisplay paymentModeDTO=_paymentModeRepository.findById(paymentModeId).map(PaymentModeDTOForDisplay::mapToDto).orElse(null);

        if (paymentModeDTO==null)
            exception.add("Payment not found for provided id");
        if (!exception.isEmpty())
            throw new ValidationException(String.join(",\n", exception));
        return paymentModeDTO;
    }

    public PaymentModeDTOForDisplay create(PaymentModeDTOForCreate paymentModeDTO){
        List<String> exception=new ArrayList<>();

        if (paymentModeDTO.getPaymentMode().isBlank() || paymentModeDTO.getPaymentSubMode().isBlank())
            exception.add("Invalid details");
        if (!exception.isEmpty())
            throw new ValidationException(String.join(",\n", exception));
        PaymentMode paymentMode=PaymentModeDTOForCreate.mapToEntity(paymentModeDTO,new PaymentMode());
        return PaymentModeDTOForDisplay.mapToDto(_paymentModeRepository.save(paymentMode));
    }

    public PaymentModeDTOForDisplay update(Integer paymentModeId,PaymentModeDTOForCreate paymentModeDTO){
        List<String> exception=new ArrayList<>();

        if (paymentModeId<=0 || paymentModeDTO.getPaymentMode().isBlank() || paymentModeDTO.getPaymentSubMode().isBlank())
            exception.add("Invalid details");
        PaymentMode paymentMode=_paymentModeRepository.findById(paymentModeId).orElse(null);

        if (paymentMode==null)
            exception.add("Payment mode is not present for given id");
        if (!exception.isEmpty())
            throw new ValidationException(String.join(",\n", exception));
        PaymentModeDTOForCreate.mapToEntity(paymentModeDTO,paymentMode);
        return PaymentModeDTOForDisplay.mapToDto(_paymentModeRepository.save(paymentMode));
    }

    public void delete(Integer paymentModeId){
        _paymentModeRepository.deleteById(paymentModeId);
    }
}
