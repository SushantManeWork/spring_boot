package com.shoppy.Shoppy.repository;


import com.shoppy.Shoppy.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress, Integer> {

    UserAddress findByUserUserId(Integer userId);
}
