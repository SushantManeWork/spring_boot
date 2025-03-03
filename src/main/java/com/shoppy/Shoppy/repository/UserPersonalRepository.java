package com.shoppy.Shoppy.repository;

import com.shoppy.Shoppy.entity.UserPersonal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPersonalRepository extends JpaRepository<UserPersonal, Integer> {

    boolean existsByUserUserId(Integer userId);

    UserPersonal findByUserUserId(Integer userId);
}
