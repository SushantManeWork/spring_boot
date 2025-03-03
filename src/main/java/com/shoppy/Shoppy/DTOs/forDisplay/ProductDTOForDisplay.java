package com.shoppy.Shoppy.DTOs.forDisplay;

import com.shoppy.Shoppy.entity.ProductTypes;
import com.shoppy.Shoppy.entity.Products;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProductDTOForDisplay {

    private Integer productId ;

    private String name ;

    private Integer price ;

    private Integer quantity ;

    private Integer sellQuantity ;

    private Integer productTypeId;

    private ProductTypesDTOForDisplay productType;

    public static ProductDTOForDisplay mapToDto(Products products){
        ProductDTOForDisplay productDTO=new ProductDTOForDisplay();
        productDTO.setProductId(products.getProductId());
        productDTO.setName(products.getProductName());
        productDTO.setPrice(products.getPrice());
        productDTO.setQuantity(products.getQuantity());
        productDTO.setSellQuantity(products.getSellQuantity());
        productDTO.setProductTypeId(products.getProductTypes()==null?null:products.getProductTypes().getProductTypeId());
        productDTO.setProductType(products.getProductTypes()==null?null:ProductTypesDTOForDisplay.mapToDto(products.getProductTypes()));
        return productDTO;
    }

}
