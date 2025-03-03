package com.shoppy.Shoppy.repository;

import com.shoppy.Shoppy.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus,Integer> {
}
