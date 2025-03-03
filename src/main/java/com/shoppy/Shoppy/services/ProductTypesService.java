package com.shoppy.Shoppy.services;

import com.shoppy.Shoppy.DTOs.forCreate.ProductTypesDTOForCreate;
import com.shoppy.Shoppy.DTOs.forDisplay.ProductTypesDTOForDisplay;
import com.shoppy.Shoppy.entity.ProductTypes;
import com.shoppy.Shoppy.exception.ValidationException;
import com.shoppy.Shoppy.repository.ProductTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductTypesService {

    @Autowired
    private ProductTypesRepository productTypesRepository;

    public List<ProductTypesDTOForDisplay> findAll(){
        List<ProductTypes> productTypes=productTypesRepository.findAll();
        return productTypes.stream().filter(ProductTypes::getIsActive).map(ProductTypesDTOForDisplay::mapToDto).toList();
    }

    public ProductTypesDTOForDisplay get(Integer productTypeId){
        List<String> exception=new ArrayList<>();
        if (productTypeId<=0)
            exception.add("Id is wrong");
        ProductTypesDTOForDisplay productTypesDTO= productTypesRepository.findById(productTypeId).map(ProductTypesDTOForDisplay::mapToDto).orElse(null);
        if (productTypesDTO==null)
            exception.add("Product type not found");
        if (!exception.isEmpty())
            throw new ValidationException(String.join(",\n", exception));
        return productTypesDTO;
    }

    public ProductTypesDTOForDisplay create(ProductTypesDTOForCreate productTypesDTO){
        List<String> exception=new ArrayList<>();
        if (productTypesDTO.getProductTypeName().isBlank())
            exception.add("Invalid details");
        if (!exception.isEmpty())
            throw new ValidationException(String.join(",\n", exception));

        ProductTypes productTypes=ProductTypesDTOForCreate.mapToEntity(productTypesDTO,new ProductTypes());
        return ProductTypesDTOForDisplay.mapToDto(productTypesRepository.save(productTypes));
    }

    public ProductTypesDTOForDisplay update(Integer productTypeId,ProductTypesDTOForCreate productTypesDTO){
        List<String> exception=new ArrayList<>();
        if (productTypeId<=0 || productTypesDTO.getProductTypeName().isBlank())
            exception.add("Invalid details");

        ProductTypes productTypes=productTypesRepository.findById(productTypeId).orElse(null);
        if (productTypes==null)
            exception.add("Product type not found for provided id");
        if (!exception.isEmpty())
            throw new ValidationException(String.join(",\n", exception));

        ProductTypesDTOForCreate.mapToEntity(productTypesDTO,productTypes);
        return ProductTypesDTOForDisplay.mapToDto(productTypesRepository.save(productTypes));
    }

    public void delete(Integer productTypeId){
        productTypesRepository.deleteById(productTypeId);
    }
}
