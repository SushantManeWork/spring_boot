package com.shoppy.Shoppy.controller;

import com.shoppy.Shoppy.DTOs.forCreate.ProductTypesDTOForCreate;
import com.shoppy.Shoppy.DTOs.forDisplay.ProductTypesDTOForDisplay;
import com.shoppy.Shoppy.services.ProductTypesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/papi/productTypes")
public class ProductTypesController {

    @Autowired
    private ProductTypesService productTypesService;

    @GetMapping
    public ResponseEntity<List<ProductTypesDTOForDisplay>> getAllProductTypes() {
        return ResponseEntity.ok(productTypesService.findAll());
    }

    @GetMapping("/{productTypeId}")
    public ResponseEntity<ProductTypesDTOForDisplay> getProductTypes(@PathVariable(name = "productTypeId") final Integer productTypeId) {
        ProductTypesDTOForDisplay productTypesDTO=productTypesService.get(productTypeId);
        if (productTypesDTO==null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(productTypesService.get(productTypeId));
    }

    @PostMapping
    public ResponseEntity<ProductTypesDTOForDisplay> createProductTypes(@RequestBody @Valid final ProductTypesDTOForCreate productTypesDTO) {
        final ProductTypesDTOForDisplay productTypesDTO1 = productTypesService.create(productTypesDTO);
        if (productTypesDTO1==null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.status(HttpStatus.CREATED).body(productTypesDTO1);
    }

    @PutMapping("/{productTypeId}")
    public ResponseEntity<ProductTypesDTOForDisplay> updateProductType(@PathVariable(name = "productTypeId") final Integer productTypeId,
                                                    @RequestBody @Valid final ProductTypesDTOForCreate productTypesDTO) {
        ProductTypesDTOForDisplay productTypesDTO1=productTypesService.update(productTypeId, productTypesDTO);
        if (productTypesDTO1==null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(productTypesDTO1);
    }

    @DeleteMapping("/{productTypeId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable(name = "productTypeId") final Integer productTypeId) {
        productTypesService.delete(productTypeId);
        return ResponseEntity.noContent().build();
    }
}
