package com.shoppy.Shoppy.repository;

import com.shoppy.Shoppy.entity.DeliveryTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryTypesRepository extends JpaRepository<DeliveryTypes,Integer> {
}
