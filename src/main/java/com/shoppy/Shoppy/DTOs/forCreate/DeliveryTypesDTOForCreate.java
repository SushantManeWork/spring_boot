package com.shoppy.Shoppy.DTOs.forCreate;

import com.shoppy.Shoppy.entity.DeliveryTypes;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryTypesDTOForCreate {

    @NotNull
    @Size(max = 20)
    private String deliveryType ;

    public static DeliveryTypes mapToEntity(DeliveryTypesDTOForCreate deliveryTypesDTOForCreate, DeliveryTypes deliveryTypes){
        deliveryTypes.setDeliveryType(deliveryTypesDTOForCreate.getDeliveryType());
        return deliveryTypes;
    }
}
