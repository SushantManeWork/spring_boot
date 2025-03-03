package com.shoppy.Shoppy.repository;


import com.shoppy.Shoppy.entity.UserPhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPhoneRepository extends JpaRepository<UserPhone, Integer> {

    UserPhone findByPhone(String phone);

    @Query(value="select u from UserPhone u where u.user.userId=?1 and u.isPrimary="+true)
    UserPhone findByUserIdAndIsPrimary(Integer userId);
}
