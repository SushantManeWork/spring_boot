package com.shoppy.Shoppy.DTOs.forDisplay;

import com.shoppy.Shoppy.entity.ProductTypes;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductTypesDTOForDisplay {

//    private Integer productTypeId ;

    private String typeName ;

    private Boolean isActive ;

    public static ProductTypesDTOForDisplay mapToDto(ProductTypes productTypes){
        ProductTypesDTOForDisplay productTypesDTO=new ProductTypesDTOForDisplay();
//        productTypesDTO.setProductTypeId(productTypes.getProductTypeId());
        productTypesDTO.setTypeName(productTypes.getProductTypeName());
        productTypesDTO.setIsActive(productTypes.getIsActive());
        return productTypesDTO;
    }

}
