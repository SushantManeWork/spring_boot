package com.shoppy.Shoppy.controller;

import com.shoppy.Shoppy.DTOs.forRequest.DeliveryTypesDTOForCreate;
import com.shoppy.Shoppy.DTOs.forResponse.DeliveryTypesDTOForDisplay;
import com.shoppy.Shoppy.services.DeliveryTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/papi/deliveryTypes")
public class DeliveryTypesController {

    @Autowired
    private DeliveryTypesService _deliveryTypesService;

    @GetMapping
    public ResponseEntity<List<DeliveryTypesDTOForDisplay>> getAllDeliveryTypes(){
        return ResponseEntity.ok(_deliveryTypesService.findAll());
    }

    @GetMapping("/{deliveryTypeId}")
    public ResponseEntity<DeliveryTypesDTOForDisplay> getDeliveryType(@PathVariable("deliveryTypeId") Integer deliveryTypeId){
        DeliveryTypesDTOForDisplay deliveryTypesDTO=_deliveryTypesService.get(deliveryTypeId);
        return ResponseEntity.ok(deliveryTypesDTO);
    }

    @PostMapping
    public ResponseEntity<DeliveryTypesDTOForDisplay> createDeliveryTypes(@RequestBody DeliveryTypesDTOForCreate deliveryTypesDTO){
        DeliveryTypesDTOForDisplay deliveryTypesDTO1=_deliveryTypesService.create(deliveryTypesDTO);

        if (deliveryTypesDTO1==null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.status(HttpStatus.CREATED).body(deliveryTypesDTO1);
    }

    @PutMapping("/{deliveryTypeId}")
    public ResponseEntity<DeliveryTypesDTOForDisplay> updateDeliveryTypes(@PathVariable("deliveryTypeId") Integer deliveryTypeId,@RequestBody DeliveryTypesDTOForCreate deliveryTypesDTO){
        DeliveryTypesDTOForDisplay deliveryTypesDTO1=_deliveryTypesService.update(deliveryTypeId,deliveryTypesDTO);

        if (deliveryTypesDTO1==null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.status(HttpStatus.CREATED).body(deliveryTypesDTO1);
    }

    @DeleteMapping("/{deliveryTypeId}")
    public ResponseEntity<Void> deleteDeliveryTypes(@PathVariable("deliveryTypeId")Integer deliveryTypeId){
        _deliveryTypesService.delete(deliveryTypeId);
        return ResponseEntity.noContent().build();
    }


}
