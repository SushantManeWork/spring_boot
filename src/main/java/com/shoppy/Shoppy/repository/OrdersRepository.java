package com.shoppy.Shoppy.repository;

import com.shoppy.Shoppy.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdersRepository extends JpaRepository<Orders,Integer> {


    Optional<Orders> findByUserUserId(Integer userId);
}
