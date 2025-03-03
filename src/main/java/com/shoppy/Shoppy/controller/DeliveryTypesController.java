package com.shoppy.Shoppy.controller;

import com.shoppy.Shoppy.DTOs.forCreate.DeliveryTypesDTOForCreate;
import com.shoppy.Shoppy.DTOs.forDisplay.DeliveryTypesDTOForDisplay;
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
    private DeliveryTypesService deliveryTypesService;

    @GetMapping
    public ResponseEntity<List<DeliveryTypesDTOForDisplay>> getAllDeliveryTypes(){
        return ResponseEntity.ok(deliveryTypesService.findAll());
    }

    @GetMapping("/{deliveryTypeId}")
    public ResponseEntity<?> getDeliveryType(@PathVariable("deliveryTypeId") Integer deliveryTypeId){
        try {
            DeliveryTypesDTOForDisplay deliveryTypesDTO=deliveryTypesService.get(deliveryTypeId);
            return ResponseEntity.ok(deliveryTypesDTO);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<DeliveryTypesDTOForDisplay> createDeliveryTypes(@RequestBody DeliveryTypesDTOForCreate deliveryTypesDTO){
        DeliveryTypesDTOForDisplay deliveryTypesDTO1=deliveryTypesService.create(deliveryTypesDTO);
        if (deliveryTypesDTO1==null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.status(HttpStatus.CREATED).body(deliveryTypesDTO1);
    }

    @PutMapping("/{deliveryTypeId}")
    public ResponseEntity<DeliveryTypesDTOForDisplay> updateDeliveryTypes(@PathVariable("deliveryTypeId") Integer deliveryTypeId,@RequestBody DeliveryTypesDTOForCreate deliveryTypesDTO){
        DeliveryTypesDTOForDisplay deliveryTypesDTO1=deliveryTypesService.update(deliveryTypeId,deliveryTypesDTO);
        if (deliveryTypesDTO1==null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.status(HttpStatus.CREATED).body(deliveryTypesDTO1);
    }

    @DeleteMapping("/{deliveryTypeId}")
    public ResponseEntity<Void> deleteDeliveryTypes(@PathVariable("deliveryTypeId")Integer deliveryTypeId){
        deliveryTypesService.delete(deliveryTypeId);
        return ResponseEntity.noContent().build();
    }


}
