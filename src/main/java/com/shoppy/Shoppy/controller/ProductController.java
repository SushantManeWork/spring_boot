package com.shoppy.Shoppy.controller;

import com.shoppy.Shoppy.DTOs.forCreate.ProductDTOForCreate;
import com.shoppy.Shoppy.DTOs.forDisplay.ProductDTOForDisplay;
import com.shoppy.Shoppy.services.ProductsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/papi/products")
public class ProductController {

    @Autowired
    private ProductsService productsService;

    @GetMapping
    public ResponseEntity<List<ProductDTOForDisplay>> getAllProducts() {
        return ResponseEntity.ok(productsService.findAll());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTOForDisplay> getProduct(@PathVariable(name = "productId") final Integer productId) {
        ProductDTOForDisplay productDTO=productsService.get(productId);
        if (productDTO==null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(productDTO);
    }

    @PostMapping
    public ResponseEntity<ProductDTOForDisplay> createProduct(@RequestBody @Valid final ProductDTOForCreate productDTO) {
        final ProductDTOForDisplay productDTO1 = productsService.create(productDTO);
        if (productDTO1==null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.status(HttpStatus.CREATED).body(productDTO1);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductDTOForDisplay> updateProduct(@PathVariable(name = "productId") final Integer productId,
                                                @RequestBody @Valid final ProductDTOForCreate productDTO) {
        ProductDTOForDisplay productDTO1=productsService.update(productId, productDTO);
        if (productDTO1==null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(productDTO1);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable(name = "productId") final Integer productId) {
        productsService.delete(productId);
        return ResponseEntity.noContent().build();
    }
}
