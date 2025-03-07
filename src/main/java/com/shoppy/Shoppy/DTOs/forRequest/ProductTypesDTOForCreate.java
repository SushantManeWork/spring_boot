package com.shoppy.Shoppy.DTOs.forRequest;

import com.shoppy.Shoppy.entity.ProductTypes;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductTypesDTOForCreate {

    private String productTypeName ;

    private Boolean isActive ;


    public static ProductTypes mapToEntity(ProductTypesDTOForCreate productTypesDTO, ProductTypes productTypes){
        productTypes.setProductTypeName(productTypesDTO.getProductTypeName());
        productTypes.setIsActive(productTypesDTO.getIsActive());
        return productTypes;
    }
}
