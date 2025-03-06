package com.shoppy.Shoppy.services;

import com.shoppy.Shoppy.DTOs.forCreate.DeliveryTypesDTOForCreate;
import com.shoppy.Shoppy.DTOs.forDisplay.DeliveryTypesDTOForDisplay;
import com.shoppy.Shoppy.entity.DeliveryTypes;
import com.shoppy.Shoppy.exception.ValidationException;
import com.shoppy.Shoppy.repository.DeliveryTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class DeliveryTypesService {

    @Autowired
    private DeliveryTypesRepository _deliveryTypesRepository;

    public List<DeliveryTypesDTOForDisplay> findAll(){
        List<DeliveryTypes> deliveryTypes=_deliveryTypesRepository.findAll();
        return deliveryTypes.stream().map(DeliveryTypesDTOForDisplay::mapToDto).toList();
    }

    public DeliveryTypesDTOForDisplay get(Integer deliveryTypeId){
        List<String> exception=new ArrayList<>();

        if (deliveryTypeId<=0)
            exception.add("Id is wrong");
        DeliveryTypesDTOForDisplay deliveryTypesDTO=_deliveryTypesRepository.findById(deliveryTypeId).map(DeliveryTypesDTOForDisplay::mapToDto).orElse(null);

        if (deliveryTypesDTO==null)
            exception.add("Delivery type not found for provided id");

        if (!exception.isEmpty())
            throw new ValidationException(String.join(",\n", exception));
        return deliveryTypesDTO;
    }

    public DeliveryTypesDTOForDisplay create(DeliveryTypesDTOForCreate deliveryTypesDTO){
        List<String> exception=new ArrayList<>();

        if (deliveryTypesDTO.getDeliveryType().isBlank())
            exception.add("Invalid details");

        if (!exception.isEmpty())
            throw new ValidationException(String.join(",\n", exception));

        final DeliveryTypes deliveryTypes=DeliveryTypesDTOForCreate.mapToEntity(deliveryTypesDTO,new DeliveryTypes());
        return DeliveryTypesDTOForDisplay.mapToDto(_deliveryTypesRepository.save(deliveryTypes));
    }

    public DeliveryTypesDTOForDisplay update(Integer deliveryTypeId,DeliveryTypesDTOForCreate deliveryTypesDTO){
        List<String> exception=new ArrayList<>();

        if (deliveryTypeId<=0 || deliveryTypesDTO.getDeliveryType().isBlank())
            exception.add("Invalid details");

        DeliveryTypes deliveryTypes=_deliveryTypesRepository.findById(deliveryTypeId).orElse(null);

        if (deliveryTypes==null)
            exception.add("Delivery type not found for provided id");

        if (!exception.isEmpty())
            throw new ValidationException(String.join(",\n", exception));
        DeliveryTypesDTOForCreate.mapToEntity(deliveryTypesDTO,deliveryTypes);
        deliveryTypes.setDeliveryTypeId(deliveryTypeId);
        return DeliveryTypesDTOForDisplay.mapToDto(_deliveryTypesRepository.save(deliveryTypes));
    }

    public void delete(Integer deliveryTypeId){
        _deliveryTypesRepository.deleteById(deliveryTypeId);
    }
}
