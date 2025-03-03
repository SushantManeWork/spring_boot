package com.shoppy.Shoppy.repository;

import com.shoppy.Shoppy.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository<Products,Integer> {
}
