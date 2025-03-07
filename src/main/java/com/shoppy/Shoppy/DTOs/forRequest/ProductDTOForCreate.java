package com.shoppy.Shoppy.DTOs.forRequest;

import com.shoppy.Shoppy.entity.ProductTypes;
import com.shoppy.Shoppy.entity.Products;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTOForCreate {

    private String productName ;

    private Integer price ;

    private Integer quantity ;

    private Integer productTypes;

    public static Products mapToEntity(ProductDTOForCreate productDTO, Products products){
        products.setProductName(productDTO.getProductName());
        products.setPrice(productDTO.getPrice());
        products.setQuantity(productDTO.getQuantity());
        products.setSellQuantity(products.getSellQuantity()!=null?products.getSellQuantity():0);
        products.setProductTypes(productDTO.getProductTypes()==null?null:new ProductTypes(productDTO.getProductTypes()));
        return products;
    }

}
