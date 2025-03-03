package com.shoppy.Shoppy.repository;

import com.shoppy.Shoppy.entity.ProductTypes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTypesRepository extends JpaRepository<ProductTypes,Integer> {

//    @Override
//    Page<ProductTypes> findAll(Pageable pageable);
}
