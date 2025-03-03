package com.shoppy.Shoppy.repository;

import com.shoppy.Shoppy.entity.PaymentMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentModeRepository extends JpaRepository<PaymentMode,Integer> {
}
