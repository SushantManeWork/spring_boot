package com.shoppy.Shoppy.DTOs.forDisplay;

import com.shoppy.Shoppy.entity.DeliveryTypes;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryTypesDTOForDisplay {

    private Integer deliveryTypeId;

    @NotNull
    @Size(max = 20)
    private String deliveryType ;

    public static DeliveryTypesDTOForDisplay mapToDto(DeliveryTypes deliveryTypes){
        DeliveryTypesDTOForDisplay deliveryTypesDTO=new DeliveryTypesDTOForDisplay();
        deliveryTypesDTO.setDeliveryTypeId(deliveryTypes.getDeliveryTypeId());
        deliveryTypesDTO.setDeliveryType(deliveryTypes.getDeliveryType());
        return  deliveryTypesDTO;
    }
}
