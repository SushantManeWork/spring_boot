package com.shoppy.Shoppy.services;

import com.shoppy.Shoppy.DTOs.forCreate.ProductDTOForCreate;
import com.shoppy.Shoppy.DTOs.forDisplay.ProductDTOForDisplay;
import com.shoppy.Shoppy.entity.ProductTypes;
import com.shoppy.Shoppy.entity.Products;
import com.shoppy.Shoppy.exception.ValidationException;
import com.shoppy.Shoppy.repository.ProductTypesRepository;
import com.shoppy.Shoppy.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductsService {

    @Autowired
    private ProductsRepository _productsRepository;

    @Autowired
    private ProductTypesRepository _productTypesRepository;

    public List<ProductDTOForDisplay> findAll(){
        List<Products> products=_productsRepository.findAll();
        return products.stream().map(ProductDTOForDisplay::mapToDto).toList();
    }

    public  ProductDTOForDisplay get(Integer productId){
        List<String> exception=new ArrayList<>();

        if (productId<=0)
            exception.add("Id is wrong");
        ProductDTOForDisplay productDTO=_productsRepository.findById(productId).map(ProductDTOForDisplay::mapToDto).orElse(null);

        if (productDTO==null)
            exception.add("Product not found for provided id");
        if (!exception.isEmpty())
            throw new ValidationException(String.join(",\n", exception));
        return productDTO;
    }

    public ProductDTOForDisplay create(ProductDTOForCreate productDTO){
        List<String> exception=new ArrayList<>();

        if (productDTO.getProductName().isBlank() || productDTO.getQuantity()<=0 || productDTO.getPrice()<=0)
            exception.add("Invalid details");
        ProductTypes productTypes=_productTypesRepository.findById(productDTO.getProductTypes()).orElse(null);

        if (productTypes != null && !productTypes.getIsActive())
            exception.add("Product Type is not active");
        if (!exception.isEmpty())
            throw new ValidationException(String.join(",\n", exception));
        Products products=ProductDTOForCreate.mapToEntity(productDTO,new Products());
        return ProductDTOForDisplay.mapToDto(_productsRepository.save(products));
    }

    public ProductDTOForDisplay update(Integer productId,ProductDTOForCreate productDTO){
        List<String> exception=new ArrayList<>();

        if (productId<=0 || productDTO.getProductName().isBlank() || productDTO.getQuantity()<=0 || productDTO.getPrice()<=0)
            exception.add("Invalid details");
        ProductTypes productTypes=_productTypesRepository.findById(productDTO.getProductTypes()).orElse(null);

        if (productTypes != null && !productTypes.getIsActive())
            exception.add("Product Type is not active");
        Products products=_productsRepository.findById(productId).orElse(null);

        if (products!=null)
            exception.add("Product is not found for provided id");
        if (!exception.isEmpty())
            throw new ValidationException(String.join(",\n", exception));

        ProductDTOForCreate.mapToEntity(productDTO,products);
        return ProductDTOForDisplay.mapToDto(_productsRepository.save(products));
    }

    public void delete(Integer productId){
        _productsRepository.deleteById(productId);
    }

}
